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

    /** Metoda dodajaca wiadomosc
     * @param tick tik
     * @param allLivings wszystkich zyjacych zwierzat
     * @param carnivores miesozercy
     * @param herbivores roslinozercy
     */
    public void addMessage(int tick, int allLivings, int carnivores, int herbivores) {
        logBuilder.append(tick + ";" + allLivings + ";" + carnivores + ";" + herbivores  + "\n");
    }

    /** Metoda zapisujaca pliki
     * @param filename nazwa pliku
     */
    public void saveFile(String filename) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".csv"))) {
            writer.write(logBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } ;
    }

    /** Wiadomosc poczatkowa
     *
     */
    private void appendStartMessage() {
        logBuilder.append("tick;wszystkich;roslinozercy;miesozercy").append("\n");
    }


}
