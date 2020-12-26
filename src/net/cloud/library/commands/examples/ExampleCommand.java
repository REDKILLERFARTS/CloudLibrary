package net.cloud.library.commands.examples;

import net.cloud.library.commands.CloudCommandBuilder;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class ExampleCommand extends CloudCommandBuilder {

    public ExampleCommand() {
        super("Example");
        setCommandDescription("A custom command description")
                .setCommandAliases(Arrays.asList("examplef", "examples"));
    }

    @Override
    public boolean onCommand(CommandSender sender, String label, String[] args) {
        sender.sendMessage("Hello! : " + sender.getName());
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String label, String[] args) {
        return Arrays.asList("CHUB");
    }
}
