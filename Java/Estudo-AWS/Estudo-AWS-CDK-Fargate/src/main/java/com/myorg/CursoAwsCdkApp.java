package com.myorg;

import software.amazon.awscdk.core.App;

public class CursoAwsCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        //Criando a Vpc declarada na Classe VpcStack
        VpcStack vpc = new VpcStack(app, "Vpc");

        /*
         * O Vpc usado será o mesmo criado no passo anterior, por isso na classe eu exponho a VPC
         * criada para poder declará-la aqui
         * */
        ClusterStack cluster = new ClusterStack(app, "Cluster", vpc.getVpc());
        //(Como eu preciso da VPC pra criar o cluster eu tenho uma dependencia, preciso declarar isso)
        cluster.addDependency(vpc);

        //Criando a stack de rds e dizendo que ela depende da VPC
        RdsStack rdsStack = new RdsStack(app, "Rds", vpc.getVpc());
        rdsStack.addDependency(vpc);

        SnsStack snsStack = new SnsStack(app, "Sns");

        /*
         * Como o Cluster será o mesmo criado no passo anterior e o mesmo ja está registrado num VPC, por isso na classe ClusterStack
         * eu exponho o cluster criada para poder declará-la aqui
         * */
        Service01Stack service01Stack = new Service01Stack(app, "Service01", cluster.getCluster(), snsStack.getProductEventsTopic());
        service01Stack.addDependency(cluster);
        service01Stack.addDependency(rdsStack);
        service01Stack.addDependency(snsStack);

        app.synth();
    }
}
