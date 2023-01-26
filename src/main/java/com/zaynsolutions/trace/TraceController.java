package com.zaynsolutions.trace;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.cloud.opentelemetry.trace.TraceConfiguration;
import com.google.cloud.opentelemetry.trace.TraceExporter;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.List;
import java.util.ListIterator;
import com.zaynsolutions.trace.beans.Root;
@RestController
public class TraceController {

	@PostMapping("/traces")
	public ResponseMessage newTraceData(@RequestBody String traces) {

		System.out.println("This is a test trace application v1.6");		
		System.out.println(traces);
		forwardTraces(traces);
		return new ResponseMessage("Thanks for sending traces data v1.6");
	}	


	public void forwardTraces(String traces) {
		// Using default project ID and Credentials
		// Convert json traces to Java Objects
		Root resourceSpaRoot=new JasonToObjects().jsonToJava(traces);
		
		// Set Trace Config 
		TraceConfiguration configuration =
			TraceConfiguration.builder().setDeadline(Duration.ofMillis(30000)).build();
	
		try {
			//Create Trace Exporter
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
			.build();			
		  // Extract the Tracer to use in Span creation		
		  Tracer tracer = otel.getTracer("com.zaynsolutions.config.TraceExporterApp");
		  Context context=otel.getPropagators().getTextMapPropagator().extract(Context.current(), null, null);

		  //Create Child Traces		
		  createCloudTraceSpan(tracer,resourceSpaRoot,context);

		  //Close OpenTelemetry SDK
		  otel.getSdkTracerProvider().shutdown();
		} catch (IOException e) {
		  e.printStackTrace();
		  System.out.println("Uncaught Exception");
		}
	  }

	public void createCloudTraceSpan(Tracer tracer, Root resourceSpanRoot, Context context) {
		Span parentSpan = tracer.spanBuilder("chrome-extension-spans").startSpan();
		try (Scope scope = parentSpan.makeCurrent()) {
		if(resourceSpanRoot!=null){
			List<com.zaynsolutions.trace.beans.ResourceSpan> resourceSpanList=resourceSpanRoot.getResourceSpans();
			ListIterator<com.zaynsolutions.trace.beans.ResourceSpan> resourceSpanListIterator = resourceSpanList.listIterator();
			while (resourceSpanListIterator.hasNext()) {
				com.zaynsolutions.trace.beans.ResourceSpan rspan=resourceSpanListIterator.next();
				if(rspan!=null){
					List<com.zaynsolutions.trace.beans.ScopeSpan> scopeSpan=rspan.getScopeSpans();
					if(scopeSpan!=null){
						ListIterator<com.zaynsolutions.trace.beans.ScopeSpan> scopeSpanListIterator = scopeSpan.listIterator();
						while (scopeSpanListIterator.hasNext()) {
							com.zaynsolutions.trace.beans.ScopeSpan sspan=scopeSpanListIterator.next();
							List<com.zaynsolutions.trace.beans.Span> customSpanList=sspan.getSpans();
							ListIterator<com.zaynsolutions.trace.beans.Span> customSpanListIterator = customSpanList.listIterator();
								while(customSpanListIterator.hasNext()){
									com.zaynsolutions.trace.beans.Span customspan=customSpanListIterator.next();
									Span childSpan = tracer.spanBuilder(customspan.getName()+"-"+customspan.getSpanId()).setParent(context.with(parentSpan)).startSpan();	
									childSpan.setAttribute("name", customspan.getName());
									childSpan.setAttribute("spanid", customspan.getSpanId());
									childSpan.end();
								}
						}	
					}
					}
				}
			}
		}
		finally {
			parentSpan.end();
		}
	}

	
	@GetMapping("/")
	public String returnResponse() {
		System.out.println("This is a test treace application v1.1.");		
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
			.build();
			//.buildAndRegisterGlobal();
	
		  Tracer tracer = otel.getTracer("com.zaynsolutions.config.TraceExporterApp");
		  // Application-specific logic
		  createSpan("http://localhost:8080",tracer);
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
