package com.lcc.demo.design_patterns;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author lcc
 * @version 2019-06-01
 */
public class Task1 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入第一个数");
    String firstNum = scanner.next();
    if (firstNum == null || "".equals(firstNum)) {
      throw new IllegalArgumentException();
    }
    Double firstNumber = Double.valueOf(firstNum);
    System.out.println("请输入以下操作符: +、-、*、/");
    String operation = scanner.next();
    if (operation == null || "".equals(operation)) {
      throw new IllegalArgumentException();
    }
    Operation op = OperationFactory.getOperation(operation);
    System.out.println("请输入第二个数");
    String secondNum = scanner.next();
    if (secondNum == null || "".equals(secondNum)) {
      throw new IllegalArgumentException();
    }
    Double secondNumber = Double.valueOf(secondNum);
    Double result = op.calculate(firstNumber, secondNumber);
    System.out.println(result);

  }

  static class OperationFactory {

    private static final Map<String, Operation> OPERATION_MAP = new HashMap<>();

    static {
      OPERATION_MAP.put("+", new Plus());
      OPERATION_MAP.put("-", new Minus());
      OPERATION_MAP.put("*", new Multiply());
      OPERATION_MAP.put("/", new Divide());
    }

    static Operation getOperation(String operation) {
      Operation op = OPERATION_MAP.get(operation);
      if (op == null) {
        throw new IllegalArgumentException("操作符输入错误");
      }
      return op;
    }

    static class Plus implements Operation {

      @Override
      public Double calculate(Double firstNum, Double secondNum) {
        return new BigDecimal(firstNum.toString())
            .add(new BigDecimal(secondNum.toString()))
            .doubleValue();
      }
    }

    static class Minus implements Operation {

      @Override
      public Double calculate(Double firstNum, Double secondNum) {
        return new BigDecimal(firstNum.toString())
            .subtract(new BigDecimal(secondNum.toString()))
            .doubleValue();
      }
    }

    static class Multiply implements Operation {

      @Override
      public Double calculate(Double firstNum, Double secondNum) {
        return new BigDecimal(firstNum.toString())
            .multiply(new BigDecimal(secondNum.toString()))
            .doubleValue();
      }
    }

    static class Divide implements Operation {

      @Override
      public Double calculate(Double firstNum, Double secondNum) {
        if (0D == secondNum) {
          throw new IllegalArgumentException("被除数不可为0");
        }
        return new BigDecimal(firstNum.toString())
            .divide(new BigDecimal(secondNum.toString()), RoundingMode.HALF_UP)
            .doubleValue();
      }
    }
  }

  interface Operation {

    Double calculate(Double firstNum, Double secondNum);
  }
}
