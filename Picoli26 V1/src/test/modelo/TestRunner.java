package test.modelo;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class TestRunner {
    public static void main(String[] args) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
            .selectors(
                selectClass(EstadoPagarTest.class),
                selectClass(EstadoJubilarTest.class),
                selectClass(EstadoAbrirCerrarTest.class)
            )
            .filters(org.junit.platform.launcher.EngineFilter.excludeEngines("junit-vintage"))
            .build();

        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);

        launcher.execute(request);

        TestExecutionSummary summary = listener.getSummary();
        System.out.println("=== TEST RUN RESULTS ===");
        System.out.println("Tests started: " + summary.getTestsStartedCount());
        System.out.println("Tests succeeded: " + summary.getTestsSucceededCount());
        System.out.println("Tests failed: " + summary.getTestsFailedCount());
        
        if (summary.getTestsFailedCount() > 0) {
            System.out.println("=== FAILURES ===");
            summary.getFailures().forEach(failure -> {
                System.out.println("Test: " + failure.getTestIdentifier().getDisplayName());
                failure.getException().printStackTrace();
                System.out.println("----------------------------------------------");
            });
            System.exit(1);
        } else {
            System.out.println("All tests passed successfully!");
            System.exit(0);
        }
    }
}
