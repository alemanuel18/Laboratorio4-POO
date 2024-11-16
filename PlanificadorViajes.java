import java.util.Date;

public class PlanificadorViajes {
    private Date inicio;
    private Date fin;
    private String lugarIncio;
    private String lugarFinal;

    public PlanificadorViajes(Date inicio, Date fin, String lugarInicio, String lugarFinal){
        this.inicio=inicio;
        this.fin=fin;
        this.lugarIncio=lugarInicio;
        this.lugarFinal=lugarFinal;
    }

    public Date getFin() {
        return fin;
    }
    public Date getInicio() {
        return inicio;
    }
    public String getLugarFinal() {
        return lugarFinal;
    }

    public String getLugarIncio() {
        return lugarIncio;
    }
}
