/**
 * This program provides distance and temperature conversion utilities through a graphical user interface.
 * The user can convert distances between miles and kilometers, and temperatures between Fahrenheit and Celsius.
 * The GUI interface enhances the user-friendliness of the program.
 *
 * Author: Charles Bostwick
 * Date: June 22, 2023
 * Version: 1.03
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ConverterApp {
    /**
     * The Converter class is an abstract base class for different types of converters.
     * It provides a common field for storing the input value.
     */
    static class Converter {
        protected double input;

        /**
         * Constructs a Converter object with an uninitialized input value.
         */
        public Converter() {
            this.input = Double.NaN;
        }

        /**
         * Constructs a Converter object with the specified input value.
         *
         * @param input the input value for the converter
         */
        public Converter(double input) {
            this.input = input;
        }
    }

    static class DistanceConverter extends Converter {
        public DistanceConverter() {
            super();
        }

        public DistanceConverter(double input) {
            super(input);
        }

        /**
         * Converts miles to kilometers.
         *
         * @return the converted value in kilometers
         */
        public double milesToKilometers() {
            if (Double.isNaN(input))
                return Double.NaN;

            return roundToTwoDecimals(input * 1.609);
        }

        /**
         * Converts kilometers to miles.
         *
         * @return the converted value in miles
         */
        public double kilometersToMiles() {
            if (Double.isNaN(input))
                return Double.NaN;

            return roundToTwoDecimals(input / 1.609);
        }
    }

    static class TemperatureConverter extends Converter {
        public TemperatureConverter() {
            super();
        }

        public TemperatureConverter(double input) {
            super(input);
        }

        /**
         * Converts Fahrenheit to Celsius.
         *
         * @return the converted value in Celsius
         */
        public double fahrenheitToCelsius() {
            if (Double.isNaN(input))
                return Double.NaN;

            return roundToTwoDecimals((input - 32) * 5 / 9);
        }

        /**
         * Converts Celsius to Fahrenheit.
         *
         * @return the converted value in Fahrenheit
         */
        public double celsiusToFahrenheit() {
            if (Double.isNaN(input))
                return Double.NaN;

            return roundToTwoDecimals((input * 9 / 5) + 32);
        }
    }

    static class GUIConverter {

        private static final String INVALID_NUMERIC_INPUT = "Invalid input. Please enter a non-negative number.";

        /**
         * Creates the main window of the converter program.
         */
        public static void createMainWindow() {
            JFrame mainWindowFrame = new JFrame("Converter");
            mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(5, 5, 20, 5);

            // Converter selection label
            JLabel selectionLabel = new JLabel("Please select which Converter you would like to use:");
            selectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(selectionLabel, gbc);

            gbc.gridy = 1;
            gbc.insets = new Insets(0, 5, 5, 5);

            //Distance conversion buttons
            JPanel distancePanel = new JPanel(new GridLayout(2, 1, 5, 5));
            distancePanel.setBorder(BorderFactory.createTitledBorder("Distance"));

            JButton milesToKilometersButton = new JButton("Miles to Kilometers");
            milesToKilometersButton.setPreferredSize(new Dimension(200, 50));
            distancePanel.add(milesToKilometersButton);

            JButton kilometersToMilesButton = new JButton("Kilometers to Miles");
            kilometersToMilesButton.setPreferredSize(new Dimension(200, 50));
            distancePanel.add(kilometersToMilesButton);

            panel.add(distancePanel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;

            // Temperature conversion buttons
            JPanel temperaturePanel = new JPanel(new GridLayout(2, 1, 5, 5));
            temperaturePanel.setBorder(BorderFactory.createTitledBorder("Temperature"));

            JButton fahrenheitToCelsiusButton = new JButton("Fahrenheit to Celsius");
            fahrenheitToCelsiusButton.setPreferredSize(new Dimension(200, 50));
            temperaturePanel.add(fahrenheitToCelsiusButton);

            JButton celsiusToFahrenheitButton = new JButton("Celsius to Fahrenheit");
            celsiusToFahrenheitButton.setPreferredSize(new Dimension(200, 50));
            temperaturePanel.add(celsiusToFahrenheitButton);

            panel.add(temperaturePanel, gbc);

            // Exit button
            JButton exitButton = new JButton("Exit");
            exitButton.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.SOUTHEAST;
            gbc.insets = new Insets(20, 5, 5, 5);
            panel.add(exitButton, gbc);

            mainWindowFrame.getContentPane().add(panel);
            mainWindowFrame.pack();
            mainWindowFrame.setLocationRelativeTo(null);
            mainWindowFrame.setVisible(true);

            milesToKilometersButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputString = JOptionPane.showInputDialog(mainWindowFrame, "Enter distance in miles:");
                    try {
                        double input = Double.parseDouble(inputString);
                        DistanceConverter converter = new DistanceConverter(input);
                        double convertedValue = converter.milesToKilometers();
                        JOptionPane.showMessageDialog(mainWindowFrame, "Distance in kilometers: " + convertedValue);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(mainWindowFrame, "Invalid distance input. Please enter a valid number in miles.");
                    } catch (NullPointerException ex) {
                        // User closed the dialog without entering any input
                    }
                }
            });

            kilometersToMilesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputString = JOptionPane.showInputDialog(mainWindowFrame, "Enter distance in kilometers:");
                    try {
                        double input = Double.parseDouble(inputString);
                        DistanceConverter converter = new DistanceConverter(input);
                        double convertedValue = converter.kilometersToMiles();
                        JOptionPane.showMessageDialog(mainWindowFrame, "Distance in miles: " + convertedValue);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(mainWindowFrame, "Invalid distance input. Please enter a valid number in kilometers.");
                    } catch (NullPointerException ex) {
                        // User closed the dialog without entering any input
                    }
                }
            });

            fahrenheitToCelsiusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputString = JOptionPane.showInputDialog(mainWindowFrame, "Enter temperature in Fahrenheit:");
                    try {
                        double input = Double.parseDouble(inputString);
                        TemperatureConverter converter = new TemperatureConverter(input);
                        double convertedValue = converter.fahrenheitToCelsius();
                        JOptionPane.showMessageDialog(mainWindowFrame, "Temperature in Celsius: " + convertedValue);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(mainWindowFrame, "Invalid temperature input. Please enter a valid number in Fahrenheit.");
                    } catch (NullPointerException ex) {
                        // User closed the dialog without entering any input
                    }
                }
            });

            celsiusToFahrenheitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String inputString = JOptionPane.showInputDialog(mainWindowFrame, "Enter temperature in Celsius:");
                    try {
                        double input = Double.parseDouble(inputString);
                        TemperatureConverter converter = new TemperatureConverter(input);
                        double convertedValue = converter.celsiusToFahrenheit();
                        JOptionPane.showMessageDialog(mainWindowFrame, "Temperature in Fahrenheit: " + convertedValue);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(mainWindowFrame, "Invalid temperature input. Please enter a valid number in Celsius.");
                    } catch (NullPointerException ex) {
                        // User closed the dialog without entering any input
                    }
                }
            });

            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }
    }

    /**
     * Rounds the given value to two decimal places.
     *
     * @param value the value to round
     * @return the rounded value
     */
    private static double roundToTwoDecimals(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return Double.parseDouble(decimalFormat.format(value));
    }

    /**
     * The main method serves as the entry point of the program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUIConverter::createMainWindow);
    }
}
