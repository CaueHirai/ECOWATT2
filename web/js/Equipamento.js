/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


export default class Equipamento {
    constructor(data) {
        this.codigo = data.codigo;
        this.nome = data.nome;
        this.bloco = data.bloco;
        this.sala = data.sala;
        this.horasDiarias = data.horasDiarias;
        this.potencia = data.potencia;
        this.diasMes = data.diasMes;
        this.qtde = data.qtde;
        this.imagem = data.imagem;
    }

    consumoMensal() {
        return this.potencia * this.horasDiarias * this.diasMes * this.qtde;
    }

    temImagem() {
        return this.imagem && this.imagem.trim() !== "";
    }
}