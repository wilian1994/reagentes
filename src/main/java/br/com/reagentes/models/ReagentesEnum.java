package br.com.reagentes.models;

public enum ReagentesEnum {

	PESO_MOLECULAR("Peso molecular", "setMassaMolar", null),

	FAMILIA_QUIMICA("Família química", "setFamilia", null),

	FORMULA_MOLECULAR("Fórmula molecular", "setFormula", null),

	APARENCIA("Aparência", "setAparencia", null),

	PH("pH", "setPH", null),
	
	REATIVIDADE_OUTROS("Reatividade química com outros materiais", "setReatividade", null),

	ESTABILIDADE("Estabilidade durante o transporte", "setEstabilidade", null),

	TEMPERATURA_ARMAZENAMENTO("Temperatura e armazenamento", "setArmazenamento", null),

	NEUTRALIZACAO_DISPOSICAO("Neutralização e disposição final", "setMetodoDescarte", null),
	
	REAGE_H2O("Reatividade química com água", "setReageH2O", null),
	
	RADIOATIVIDADE("Radioatividade", "setRadioativo", null),
	
	INFLAMAVEL("Ponto de fulgor", "setInflamavel", null),

	PERIGO_SAUDE("NFPA (National Fire Protection Association)", "setPerigoSaude",
			"^Perigo de Saúde \\(.+\\): ?(\\d+) Inflamabilidade .+")

	;

	String valorTela;

	String metodo;

	String regex;

	ReagentesEnum(String valorTela, String metodo, String regex) {
		this.valorTela = valorTela;
		this.metodo = metodo;
		this.regex = regex;
	}

	public static ReagentesEnum containsValue(String valor) {
		for (ReagentesEnum e : values()) {
			if (e.valorTela.equalsIgnoreCase(valor)) {
				return e;
			}
		}
		return null;
	}

	public String getValorTela() {
		return valorTela;
	}

	public String getMetodo() {
		return metodo;
	}

	public String getRegex() {
		return regex;
	}

}
