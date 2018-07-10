package br.com.reagentes.models;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Entity
public class Reagente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment") 
	private Long id;
	private String nome;
	private String formula;
	private String massaMolar;
	private String familia;
	private String aparencia;
	private String link;
	private String PH;
	private String reageH2O;
	private String inflamavel;
	private String radioativo;
	private String perigoSaude;
	private String corrosivo;
	private String reatividade;
	private String estabilidade;
	private String armazenamento;
	private String indicador;
	private String parametroIndicacao;
	private String metodoPreparo;
	private String passivoRevalidacao;
	@Column(length = 3000) 
	private String indicacaoRevalidacao;
	private String descarte;
	private String descarteCompostos;
	@Column(length = 3000) 
	private String compostoDescarte;
	@Column(length = 3000) 
	private String metodoDescarte;
	private Status status;
	private int quantidade; 

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getMassaMolar() {
		return massaMolar;
	}

	public void setMassaMolar(String massaMolar) {
		this.massaMolar = massaMolar;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getAparencia() {
		return aparencia;
	}

	public void setAparencia(String aparencia) {
		this.aparencia = aparencia;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPH() {
		return PH;
	}

	public void setPH(String pH) {
		PH = pH;
	}

	public String getReageH2O() {
		return reageH2O;
	}

	public void setReageH2O(String reageH2O) {
		this.reageH2O = reageH2O;
	}

	public String getInflamavel() {
		return inflamavel;
	}

	public void setInflamavel(String inflamavel) {
		this.inflamavel = inflamavel;
	}

	public String getRadioativo() {
		return radioativo;
	}

	public void setRadioativo(String radioativo) {
		this.radioativo = radioativo;
	}

	public String getPerigoSaude() {
		return perigoSaude;
	}

	public void setPerigoSaude(String perigoSaude) {
		this.perigoSaude = perigoSaude;
	}

	public String getCorrosivo() {
		return corrosivo;
	}

	public void setCorrosivo(String corrosivo) {
		this.corrosivo = corrosivo;
	}

	public String getReatividade() {
		return reatividade;
	}

	public void setReatividade(String reatividade) {
		this.reatividade = reatividade;
	}

	public String getEstabilidade() {
		return estabilidade;
	}

	public void setEstabilidade(String estabilidade) {
		this.estabilidade = estabilidade;
	}

	public String getArmazenamento() {
		return armazenamento;
	}

	public void setArmazenamento(String armazenamento) {
		this.armazenamento = armazenamento;
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public String getParametroIndicacao() {
		return parametroIndicacao;
	}

	public void setParametroIndicacao(String parametroIndicacao) {
		this.parametroIndicacao = parametroIndicacao;
	}

	public String getMetodoPreparo() {
		return metodoPreparo;
	}

	public void setMetodoPreparo(String metodoPreparo) {
		this.metodoPreparo = metodoPreparo;
	}

	public String getPassivoRevalidacao() {
		return passivoRevalidacao;
	}

	public void setPassivoRevalidacao(String passivoRevalidacao) {
		this.passivoRevalidacao = passivoRevalidacao;
	}

	public String getIndicacaoRevalidacao() {
		return indicacaoRevalidacao;
	}

	public void setIndicacaoRevalidacao(String indicacaoRevalidacao) {
		this.indicacaoRevalidacao = indicacaoRevalidacao;
	}

	public String getDescarte() {
		return descarte;
	}

	public void setDescarte(String descarte) {
		this.descarte = descarte;
	}

	public String getDescarteCompostos() {
		return descarteCompostos;
	}

	public void setDescarteCompostos(String descarteCompostos) {
		this.descarteCompostos = descarteCompostos;
	}

	public String getCompostoDescarte() {
		return compostoDescarte;
	}

	public void setCompostoDescarte(String compostoDescarte) {
		this.compostoDescarte = compostoDescarte;
	}

	public String getMetodoDescarte() {
		return metodoDescarte;
	}

	public void setMetodoDescarte(String metodoDescarte) {
		this.metodoDescarte = metodoDescarte;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Reagente buscaInfo(String nome) {

		try {

			String url;
			int idx = nome.indexOf(" ");

			if (idx < 0) {
				url = "http://sistemasinter.cetesb.sp.gov.br/produtos/ficha_completa1.asp?consulta=" + nome + "";

			} else {
				nome = nome.replace(" ", "%20");
				url = "http://sistemasinter.cetesb.sp.gov.br/produtos/ficha_completa1.asp?consulta=" + nome + "";
			}

			Document document = Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url);

			Elements elements = document.getElementsByTag("table");

			if (elements != null && elements.size() > 0) {
				ReagentesEnum reagenteEnum = null;
				this.setLink(url);

				for (Element element : elements) {
					Elements titulo = element.getElementsByTag("td");
					for (Element element2 : titulo) {
						String desc = element2.getElementsByClass("font02").text();
						String desc1 = element2.getElementsByClass("font01").text();

						reagenteEnum = ReagentesEnum.containsValue(desc);

						if (reagenteEnum != null) {
							this.getClass().getDeclaredMethod(reagenteEnum.getMetodo(), String.class).invoke(this, desc1);
							if (reagenteEnum.getRegex() != null) {
								Matcher matcher = Pattern.compile(reagenteEnum.getRegex()).matcher(desc1);
								if (matcher.find()) {
									this.getClass().getDeclaredMethod(reagenteEnum.getMetodo(), String.class)
											.invoke(this, matcher.group(1));
								}
							}
						}

					}

				}

				this.setLink(url);
				
				if(this.getRadioativo().contains(" NÃO TEM.")){
					this.setRadioativo("Não");
				}else{
					this.setRadioativo("Sim");
				}
				
				System.out.println(getReageH2O());
				if(this.getReageH2O().contains(" NÃO REAGE.")){
					this.setReageH2O("Não");
				}else{
					this.setReageH2O("Sim");
				}
				
				if(this.getInflamavel().contains(" NÃO É INFLAMÁVEL.")){
					this.setInflamavel("Não");
				}else{
					this.setInflamavel("Sim");
				}
				
				
				return this;

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return this;
	}
}

