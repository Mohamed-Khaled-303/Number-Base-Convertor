package convert;
import java.math.*;

public class Calculator {
    public static String convertToDecimal(String number, int base) {
        String[] realAndFraction = number.split("\\.");
        if (realAndFraction.length > 1) {
            BigDecimal leftHansSide = new BigDecimal(convertToDecimalInt(realAndFraction[0], base));
            BigDecimal rightHandSide = new BigDecimal(convertToDecimalFromFraction(realAndFraction[1], base)).setScale(5, RoundingMode.HALF_DOWN);
            return leftHansSide.add(rightHandSide).toPlainString();
        }else
            return new BigDecimal(convertToDecimalInt(realAndFraction[0], base)).setScale(0, RoundingMode.FLOOR).toPlainString();
    }

    private static String convertToDecimalInt(String number, int base) {
        int numberLength = number.length() - 1;
        BigDecimal result = BigDecimal.ZERO;
        for (int i = 0; i < number.length(); i++) {
            int digit = Character.getNumericValue(number.charAt(i));
            double exponent = Math.pow(base, numberLength--);
            result = result.add(BigDecimal.valueOf(digit * exponent));
        }

        return result.toPlainString();
    }

    private static String convertToDecimalFromFraction(String number, int base) {
        int starterPower = 0;
        BigDecimal result = BigDecimal.ZERO;

        for (int i = 0; i < number.length(); i++) {
            int digit = Character.getNumericValue(number.charAt(i));
            double exponent = Math.pow(base, --starterPower);
            result = result.add(BigDecimal.valueOf(digit * exponent));
        }

        return result.toPlainString();
    }


    public static String convertFromDecimal(String number, int base) {
        BigDecimal fraction = new BigDecimal(number);
        BigDecimal targetBase = BigDecimal.valueOf(base);
        fraction = fraction.multiply(targetBase.pow(5)).setScale(0, RoundingMode.FLOOR);
        String hexadecimal = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder tempResult = new StringBuilder();
        int i = 0;
        while (fraction.compareTo(BigDecimal.ONE) == 1 || fraction.compareTo(BigDecimal.ONE) == 0) {
            int  reminder = Integer.parseInt(String.valueOf(fraction.remainder(targetBase).setScale(0, RoundingMode.FLOOR)));
            fraction = fraction.divide(targetBase, RoundingMode.FLOOR);

            if (i == 5)
                tempResult.append(".");

            tempResult.append(hexadecimal.charAt(reminder));
            i++;
        }

        if (i == 5)
            tempResult.append(".0");

        String result = tempResult.reverse().toString();
        String[] pair = result.split("\\.");
        if (pair[1].equals("00000"))
            return pair[0];
        else
            return result;
    }
}
