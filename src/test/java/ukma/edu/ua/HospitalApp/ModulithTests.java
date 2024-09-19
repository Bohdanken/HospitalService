package ukma.edu.ua.HospitalApp;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class ModulithTests {
    ApplicationModules modules = ApplicationModules.of(Application.class);

    @Test
    void printModules() {
        System.out.println(modules.toString());
    }

    @Test
    void shouldBeCompliant() {
        modules.verify();
    }

    @Test
    void writeDocumentationSnippets() {
        new Documenter(modules)
                .writeModuleCanvases()
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }
}
