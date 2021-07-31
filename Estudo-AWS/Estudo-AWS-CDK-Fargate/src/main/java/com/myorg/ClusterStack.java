package com.myorg;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ecs.Cluster;

public class ClusterStack extends Stack {

    /*  Esse atributo foi criado para que eu tenha acesso ao Cluster fora da classe e possa
     *  vinculá-lo ao definir aos serviços que rodarão no Cluster
     **/
    private Cluster cluster;
    /*
     * Para criação do cluester é necessario informar tambem qual a VPC
     * que o cluster estará, um escopo, que seria a classe onde eu estou 'this'
     * e mais um nome para identificar o servico.
     */
    public ClusterStack(final Construct scope, final String id, Vpc vpc) {
        this(scope, id, null, vpc);
    }

    public ClusterStack(final Construct scope, final String id, final StackProps props, Vpc vpc) {
        super(scope, id, props);

        cluster = Cluster.Builder.create(this, id)
                //Nome do Cluster
                .clusterName("cluster-01")
                //Vpc onde o Cluster estará
                .vpc(vpc)
                .build();
    }

    public Cluster getCluster() {
        return cluster;
    }
}
