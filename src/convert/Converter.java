package convert;

import java.util.Scanner;

public class Converter {
    public void convert() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
            String sourceBase = scanner.next();
            if (sourceBase.equals("/exit"))
                break;
            int targetBase = scanner.nextInt();

            while (true) {
                System.out.println("Enter number in base " + sourceBase +
                        " to convert to base " + targetBase + " (To go back type /back) ");
                String numberInSource = scanner.next();
                if (numberInSource.equals("/back"))
                    break;

                if (!sourceBase.equals("10") && targetBase != 10)
                    numberInSource = Calculator.convertToDecimal(numberInSource, Integer.parseInt(sourceBase));

                if (targetBase != 10)
                    System.out.println("Conversion result: " + Calculator.convertFromDecimal(numberInSource, targetBase));

                else
                    System.out.println("Conversion result: " + Calculator.convertToDecimal(numberInSource, Integer.parseInt(sourceBase)));
            }
        }
    }
}
