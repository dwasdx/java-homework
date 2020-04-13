package com.company;
import java.util.Random;

public class Main  {

    // Матрицы с обычными числами принимаются за матрицы с комплексными числами, у которых мнимая часть равна 0

    public static void main(String[] args) throws IrrelevantMatricesDimensions {
        complexNumbersTest();
        matricesTest();
    }

    static void complexNumbersTest() {
        ComplexNumber c1 = new ComplexNumber(1, 2);
        ComplexNumber c2 = new ComplexNumber(3, 4);
        System.out.println(c1.toTrigonometricStr());
        System.out.println(c1.add(c2));
        System.out.println(c1.sub(c2));
        System.out.println(c1.mult(c2));
        System.out.println(c2.divide(c1));
    }

    static void matricesTest() throws IrrelevantMatricesDimensions {
        Random gen = new Random();
        ComplexNumber[][] arr1 = new ComplexNumber[2][2];
        ComplexNumber[][] arr2 = new ComplexNumber[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                arr1[i][j] = new ComplexNumber(gen.nextInt() % 10, gen.nextInt() % 10);
                arr2[i][j] = new ComplexNumber(gen.nextInt() % 10, gen.nextInt() % 10);
            }
        }
        ComplexMatrix matCompl1 = new ComplexMatrix(arr1);
        System.out.println("Matrix 1");
        System.out.println(matCompl1);
        ComplexMatrix matCompl2 = new ComplexMatrix(arr2);
        System.out.println("Matrix 2");
        System.out.println(matCompl2);

        System.out.println("Matrix 1 + Matrix 2");
        System.out.println(matCompl1.add(matCompl2));
        System.out.println("Matrix 1 - Matrix 2");
        System.out.println(matCompl1.sub(matCompl2));
        System.out.println("Matrix 1 * Matrix 2");
        System.out.println(matCompl1.mult(matCompl2));
        System.out.println("Matrix 1 transponate");
        System.out.println(matCompl1.T());
    }

}
