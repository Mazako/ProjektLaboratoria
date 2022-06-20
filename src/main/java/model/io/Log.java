package model.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Log {
    private StringBuilder logBuilder;


    public Log() {
        logBuilder = new StringBuilder();
        appendStartMessage();

    }

    public void addMessage(int tick, int allLivings, int carnivores, int herbivores) {
        logBuilder.append(tick + ";" + allLivings + ";" + carnivores + ";" + herbivores  + "\n");
    }

    public void saveFile(String filename) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".csv"))) {
            writer.write(logBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } ;
    }

    private void appendStartMessage() {
        logBuilder.append("tick;wszystkich;roslinozercy;miesozercy").append("\n");
    }


}
