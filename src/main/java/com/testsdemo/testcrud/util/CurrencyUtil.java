package com.testsdemo.testcrud.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {

    private static final String[] SCALE_TH = { "ล้าน", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "" };
    private static final String[] DIGIT_TH = { "ศูนย์", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า" };
    private static final String[] SYMBOLS_TH = { "ลบ", "บาท", "ถ้วน", "สตางค์", "ยี่", "เอ็ด", "฿" };

    private static final int DEFAULT_SCALE = 2;
    private static final RoundingMode DEFAULT_ROUNDING_METHOD = RoundingMode.HALF_UP;

    public static int getDefaultScale() {
        return DEFAULT_SCALE;
    }

    public static RoundingMode getDefaultRoundingMethod() {
        return DEFAULT_ROUNDING_METHOD;
    }

    private CurrencyUtil() {
        throw new IllegalStateException("Utility Class");
    }

    public static String toNumberWithThousand(BigDecimal number) {
        return toNumberWithThousand(number, DEFAULT_SCALE);
    }

    public static String toNumberWithThousand(BigDecimal number, int scale) {
        return toNumberWithThousand(number, scale, DEFAULT_ROUNDING_METHOD);
    }

    public static String toNumberWithThousand(BigDecimal number, int scale, RoundingMode roundingMode) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        formatter.setDecimalFormatSymbols(symbols);
        return formatter.format(scaleAndRound(number, scale, roundingMode));
    }

    public static String toNumberWithThousandAndBahtSuffix(BigDecimal number) {
        return toNumberWithThousandAndBahtSuffix(number, 0);
    }

    public static String toNumberWithThousandAndBahtSuffix(BigDecimal number, int scale) {
        return toNumberWithThousand(number, scale) + " " + SYMBOLS_TH[1];
    }

    public static String toThaiBaht(BigDecimal number) {
        StringBuilder builder = new StringBuilder();
        BigDecimal absoluteNum = number.abs();

        int precision = absoluteNum.precision();
        int scale = absoluteNum.scale();
        int roundedPrecision = (precision - scale) + 2;
        MathContext mathContext = new MathContext(roundedPrecision, RoundingMode.HALF_UP);
        BigDecimal rounded = absoluteNum.round(mathContext);

        BigDecimal[] compound = rounded.divideAndRemainder(BigDecimal.ONE);
        boolean negativeNumber = number.compareTo(BigDecimal.ZERO) < 0;

        compound[0] = compound[0].setScale(0);
        compound[1] = compound[1].movePointRight(2);

        if (negativeNumber) {
            builder.append(SYMBOLS_TH[0]);
        }

        builder.append(getThaiText(compound[0].toBigIntegerExact()));
        if (compound[0].compareTo(BigDecimal.ZERO) == 0) {
            builder.append(DIGIT_TH[0]);
        }

        builder.append(SYMBOLS_TH[1]);

        if (0 == compound[1].compareTo(BigDecimal.ZERO)) {
            builder.append(SYMBOLS_TH[2]);
        } else {
            builder.append(getThaiText(compound[1].toBigIntegerExact()));
            builder.append(SYMBOLS_TH[3]);
        }
        return builder.toString();
    }

    private static String getThaiText(BigInteger number) {
        StringBuilder builder = new StringBuilder();
        char[] digits = number.toString().toCharArray();

        for (int index = digits.length; index > 0; --index) {
            int digit = Integer.parseInt(String.valueOf(digits[digits.length - index]));
            int scaleIndex = getScaleIndex(index);

            String digitText = getDigitText(digit, scaleIndex);
            if (1 == digit) {
                switch (scaleIndex) {
                    case 0:
                    case 6:
                        builder.append((index < digits.length) ? SYMBOLS_TH[5] : digitText);
                        break;
                    case 1:
                        break;
                    default:
                        builder.append(digitText);
                        break;
                }
            } else if (0 == digit) {
                if (0 == scaleIndex) {
                    builder.append(SCALE_TH[scaleIndex]);
                }
                continue;
            } else {
                builder.append(digitText);
            }
            builder.append(SCALE_TH[scaleIndex]);
        }
        return builder.toString();
    }

    private static String getDigitText(int digit, int scaleIndex) {
        if ((1 == scaleIndex) && (2 == digit)) {
            return SYMBOLS_TH[4];
        } else {
            return DIGIT_TH[digit];
        }
    }

    private static int getScaleIndex(int index) {
        return ((index > 1) ? ((index - 1) % 6) : 6);
    }

    private static BigDecimal scaleAndRound(BigDecimal value, int scale, RoundingMode mode) {
        return value.setScale(scale, mode);
    }
}