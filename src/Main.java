import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        boolean finalizar = false;

        while (!finalizar) {

            LocalDate dataVacina = pegarDataVacina(scan, formatter);

            System.out.println("Data inicial vaina: " + dataVacina);

            imprimirDatasReforco(dataVacina);

            System.out.println("\n\nDeseja calcular datas para outra vacinação? y/n");
            String repetir = scan.nextLine().toUpperCase();
            if (repetir.equals("N")) {
                finalizar = true;
                System.out.println("Até logo!");
            }
        }
        scan.close();
    }

    public static LocalDate pegarDataVacina(Scanner scan, DateTimeFormatter formatter) {
        System.out.println("Insira a data da vacina no formato dd/mm/aaaa:");
        return LocalDate.parse(scan.nextLine(), formatter);
    }

    public static void imprimirDatasReforco(LocalDate dataVacina) {

        int dosesReforco = 3;

        for (int i = 1; i <= dosesReforco; i++) {

            LocalDate diaDoseReforco = dataVacina.plusMonths(i * 3L);

            if (diaDoseReforco.getDayOfWeek() == DayOfWeek.SATURDAY) {
                diaDoseReforco = diaDoseReforco.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
            } else if (diaDoseReforco.getDayOfWeek() == DayOfWeek.SUNDAY) {
                diaDoseReforco = diaDoseReforco.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            }

            System.out.printf("Dose de reforço número %s - Data: %s\n",
                    i, diaDoseReforco);
        }
    }
}