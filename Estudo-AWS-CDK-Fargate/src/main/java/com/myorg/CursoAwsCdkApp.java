package com.myorg;

import software.amazon.awscdk.core.App;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.StackProps;

import java.util.Arrays;

public class CursoAwsCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        //Criando a Vpc declarada na Classe VpcStack
        new VpcStack(app, "Vpc");

        app.synth();
    }
}
