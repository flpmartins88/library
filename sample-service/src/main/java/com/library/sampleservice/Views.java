package com.library.sampleservice;

/**
 * Views usadas para geração do JSON a partir do Jackson
 * Cada view define um grupo de dados para ser exibido
 * Veja mais em: https://spring.io/blog/2014/12/02/latest-jackson-integration-improvements-in-spring
 * @author Felipe Martins
 */
public interface Views {

    /*
     * Serve como gambi para sempre mostrar os ids das entidades
     * Se você tem uma classe abstrata para as entidades herdarem
     * e essa classe tem a propriedade do id, basta apenas anotar
     * ela com @JsonView(Views.Base)
     */
    interface Base {}

    /**
     * Vai fazer com que apenas o nome da task seja renderizado
     */
    interface OnlyTasksName {}

    /**
     * Mostra apenas o id e nome
     */
    interface BasicTask extends Base {}

    /**
     * Mostra todos os dados da task
     */
    interface Tasks extends BasicTask {}

}
