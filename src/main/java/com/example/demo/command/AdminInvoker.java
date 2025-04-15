package com.example.demo.command;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AdminInvoker {
    private List<StockCommand> commandList = new ArrayList<>();

    public void takeCommand(StockCommand command) {
        commandList.add(command);
    }

    public void placeCommands() {
        for (StockCommand command : commandList) {
            command.execute();
        }
        commandList.clear();
    }
}
