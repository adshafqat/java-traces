package com.zaynsolutions.trace;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.cloud.opentelemetry.trace.TraceConfiguration;
import com.google.cloud.opentelemetry.trace.TraceExporter;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

@RestController
public class TraceController {

	
	@GetMapping("/")
	public String returnResponse() {
		System.out.println("This is a test treace application.");		
		createTraces();
		return "This is a test application.";
	}

	public void createTraces() {
		// Using default project ID and Credentials
		TraceConfiguration configuration =
			TraceConfiguration.builder().setDeadline(Duration.ofMillis(30000)).build();
	
		try {
		  TraceExporter traceExporter = TraceExporter.createWithConfiguration(configuration);
		  // Register the TraceExporter with OpenTelemetry
			OpenTelemetrySdk otel = OpenTelemetrySdk.builder()
			.setTracerProvider(
				SdkTracerProvider.builder()
					.addSpanProcessor(
						SimpleSpanProcessor.create(traceExporter)
					)
					.build()
			)
			.buildAndRegisterGlobal();
	
		  Tracer tracer = otel.getTracer("com.zaynsolutions.config.TraceExporterApp");
		  // Application-specific logic
		  createSpan("/firstUrl",tracer);
		  createSpan("/secondUrl",tracer);
		  otel.getSdkTracerProvider().shutdown();
		} catch (IOException e) {
		  e.printStackTrace();
		  System.out.println("Uncaught Exception");
		}
	
		// Flush all buffered traces
	  }

	private void createSpan(String description,Tracer tracer) {
		
		// Generate a span
		Span span = tracer.spanBuilder(description).startSpan();
		try (Scope scope = span.makeCurrent()) {
		  span.addEvent("Event A");
		  span.setAttribute("opentelemetry", "https://opentelemetry.io/");
		  // Add multiple child spans
		  for (int i = 0; i < 3; i++) {
			String work = String.format("%s - Work #%d", "", (i + 1));
			// Child span
			Span span1 = tracer.spanBuilder(work).startSpan();
			try (Scope scope1 = span1.makeCurrent()) {
			  // A delay betwween spans
			  Random random = new Random();
			  Thread.sleep(100 + random.nextInt(5) * 100);
			} catch (InterruptedException e) {
			  throw new RuntimeException(e);
			} finally {
			  span1.end();
			}
		  }
		  span.addEvent("Event B");
		} finally {
		  span.end();
		}
	  }
}
