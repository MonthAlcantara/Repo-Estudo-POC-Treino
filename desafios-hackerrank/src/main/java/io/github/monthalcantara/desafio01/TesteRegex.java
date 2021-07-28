package io.github.monthalcantara.desafio01;

import java.util.Scanner;

public class TesteRegex {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Digite um valor de IP para verificação");

        while (in.hasNext()) {
            System.out.println(new MyRegex().isIpValido(in.next()));
            System.out.println("Digite um valor de IP para verificação");
        }
        in.close();
    }
}
