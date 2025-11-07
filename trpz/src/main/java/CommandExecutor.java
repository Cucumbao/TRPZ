
import model.Macro;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.ExpressionEvaluator;
import org.codehaus.janino.ScriptEvaluator;
import repo.MacroRepository;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CommandExecutor {

    private final MacroRepository macroRepo;

    public CommandExecutor(MacroRepository macroRepo) {
        this.macroRepo = macroRepo;
    }

    public void  Execute(Long id) throws CompileException, InvocationTargetException {
        List<Macro> macros = macroRepo.getMacrosFromJson();
        Macro macro = macros.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);


        if (macro == null) {
            System.out.println("❌ Макрос із id " + id + " не знайдено.");
            return;
        }

        String command = macro.getCommands();
        String name = macro.getName();

        command = command.trim();
        if (command.startsWith("\"") && command.endsWith("\"")) {
            command = command.substring(1, command.length() - 1);
        }

        System.out.println("▶ Команда: " + command);

        try {
            Object result;

            if (command.contains(";")) {
                ScriptEvaluator se = new ScriptEvaluator();
                se.setDefaultImports(new String[]{"java.time.*"});
                se.cook(command);
                result = se.evaluate(null);
            } else {
                ExpressionEvaluator ee = new ExpressionEvaluator();
                ee.setExpressionType(Object.class);
                ee.setDefaultImports(new String[]{"java.time.*"});
                ee.cook(command);
                result = ee.evaluate(null);
            }

            System.out.println("✅ " + name + ": " + (result != null ? result : "(виконано успішно)"));
        } catch (CompileException | InvocationTargetException e) {
            System.out.println("❌ Помилка при виконанні макросу '" + name + "': " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠️ Інша помилка: " + e.getMessage());
        }
    }


    public static void main(String[] args) throws CompileException, InvocationTargetException {
        MacroRepository MacroRepo = new MacroRepository("src/main/java/TestData/Macros.json");
        CommandExecutor executor = new CommandExecutor(MacroRepo);
        executor.Execute(3L);
    }
}