        public class Main {
            public static void main(String[] args) {
                CalculatorService calculatorService = CalculatorFactory.createCalculatorService();
                Logger logger = CalculatorFactory.createLogger();
                CalculatorController controller = new CalculatorController(calculatorService, logger);
        
                ComplexNumber a = new ComplexNumber(5, 3);
                ComplexNumber b = new ComplexNumber(2, 4);
        
                controller.add(a, b);
                controller.multiply(a, b);
                controller.divide(a, b);
            }
        }
        
        class ComplexNumber {
            private final double real;
            private final double imaginary;
        
            public ComplexNumber(double real, double imaginary) {
                this.real = real;
                this.imaginary = imaginary;
            }
        
            public double getReal() {
                return real;
            }
        
            public double getImaginary() {
                return imaginary;
            }
        
            @Override
            public String toString() {
                return real + " + " + imaginary + "i";
            }
        }
        
        interface CalculatorService {
            ComplexNumber add(ComplexNumber a, ComplexNumber b);
            ComplexNumber multiply(ComplexNumber a, ComplexNumber b);
            ComplexNumber divide(ComplexNumber a, ComplexNumber b);
        }
        
        class ComplexCalculatorService implements CalculatorService {
        
            @Override
            public ComplexNumber add(ComplexNumber a, ComplexNumber b) {
                double real = a.getReal() + b.getReal();
                double imaginary = a.getImaginary() + b.getImaginary();
                return new ComplexNumber(real, imaginary);
            }
        
            @Override
            public ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {
                double real = a.getReal() * b.getReal() - a.getImaginary() * b.getImaginary();
                double imaginary = a.getReal() * b.getImaginary() + a.getImaginary() * b.getReal();
                return new ComplexNumber(real, imaginary);
            }
        
            @Override
            public ComplexNumber divide(ComplexNumber a, ComplexNumber b) {
                if (b.getReal() == 0 && b.getImaginary() == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");}
                double denominator = b.getReal() * b.getReal() + b.getImaginary() * b.getImaginary();
                double real = (a.getReal() * b.getReal() + a.getImaginary() * b.getImaginary()) / denominator;
                double imaginary = (a.getImaginary() * b.getReal() - a.getReal() * b.getImaginary()) / denominator;
                return new ComplexNumber(real, imaginary);
            }
        }
        
        interface Logger {
            void log(String message);
        }
        
        class ConsoleLogger implements Logger {
        
            @Override
            public void log(String message) {
                System.out.println(message);
            }
        }
        
        class CalculatorFactory {
            public static CalculatorService createCalculatorService() {
                return new ComplexCalculatorService();
            }
        
            public static Logger createLogger() {
                return new ConsoleLogger();
            }
        }
        
        class CalculatorController {
            private final CalculatorService calculatorService;
            private final Logger logger;
        
            public CalculatorController(CalculatorService calculatorService, Logger logger) {
                this.calculatorService = calculatorService;
                this.logger = logger;
            }
        
            public ComplexNumber add(ComplexNumber a, ComplexNumber b) {
                ComplexNumber result = calculatorService.add(a, b);
                logger.log("Added " + a + " to " + b + " resulting in " + result);
                return result;
            }
        
            public ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {
                ComplexNumber result = calculatorService.multiply(a, b);
                logger.log("Multiplied " + a + " with " + b + " resulting in " + result);
                return result;
            }
        
            public ComplexNumber divide(ComplexNumber a, ComplexNumber b) {
                ComplexNumber result = calculatorService.divide(a, b);
                logger.log("Divided " + a + " by " + b + " resulting in " + result);
                return result;
            }
        }
        