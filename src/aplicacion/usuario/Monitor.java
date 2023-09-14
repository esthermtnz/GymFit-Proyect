/**
 * Este fichero muestra todo lo que tiene que ver con la clase Monitor
 *
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
package aplicacion.usuario;

import aplicacion.*;
import aplicacion.actividad.TipoObjetivo;
import aplicacion.actividad.sesion.SesionMonitorizada;
import aplicacion.equipacion.Estado;
import aplicacion.equipacion.Maquina;
import aplicacion.excepciones.AveriaPreviamenteReportada;
import aplicacion.excepciones.ExcepcionUsuario;
import aplicacion.excepciones.FechaPosterior;
import aplicacion.excepciones.SueldoNoDefinido;
import aplicacion.sala.SalaSimple;
import aplicacion.actividad.sesion.Sesion;
import es.uam.eps.padsof.payrolls.IEmployeeInfo;
import es.uam.eps.padsof.payrolls.PayrollSystem;
import es.uam.eps.padsof.payrolls.exceptions.InvalidPeriod;
import es.uam.eps.padsof.payrolls.exceptions.NonExistentFileException;
import es.uam.eps.padsof.payrolls.exceptions.UnsupportedImageTypeException;
import aplicacion.actividad.Actividad;
import aplicacion.actividad.ActividadGrupal;
import aplicacion.actividad.PlanPersonalizado;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

import java.io.Serializable;
/**
 * esta es una clase que representa el monitor
 * 
 * @author Esther Martinez esther.martinezblanco@estudiante.uam.es
 * @author Celia Martin Alvarez celia.martinalvarez@estudiante.uam.es
 * @author Ana Gomez LLaneza ana.gomezl@estudiante.uam.es
 *
 */
public class Monitor extends Usuario implements IEmployeeInfo, Serializable{
	private String nombre;
	private String email;
	private Integer horasExtra=0;
	private String nif;
	
	private List <ActividadGrupal> actividadesGrupales = new ArrayList<ActividadGrupal>();
	private List <PlanPersonalizado> planesPersonalizados = new ArrayList<PlanPersonalizado>();

	/**
	 * constructor de monitor
	 * @param usuario usuario del monitor
	 * @param nif nif del monitor
	 * @param contrasenya contraseña del monitor
	 * @param nombre nombre del monitor
	 * @param email email del monitor
	 */
	public Monitor(String usuario, String nif, String contrasenya, String nombre, String email){
		super(usuario,contrasenya);
		this.nombre = nombre;
		this.email = email;
		this.nif=nif;
	}
	
	/**
	 * GETTER
	 * @return devuelve el nif
	 */
	public String getNif() {
		return this.nif;
	}
	
	/**
	 * SETTER
	 * @param nif nif que quiere poner
	 */
	public void setNif(String nif) {
		this.nif = nif;
		return;
	}

	/**
	 * GETTER
	 * @return devuelve la lista de actividades grupales
	 */
	public List <ActividadGrupal> getActividadesGrupales()
	{
		return this.actividadesGrupales;
	}
	
	/**
	 * GETTER
	 * @return devuelve la lista de actividades grupales
	 */
	public List <PlanPersonalizado> getPlanesPersonalizados()
	{
		return this.planesPersonalizados;
	}
	
	/**
	 * añade una actividad grupal a la lista de actividades
	 * @param actividadGrupal actividad a añadir
	 * @return true si se ha añadido correctamente, de lo contrario false
	 */
	public Boolean addActividadGrupal(ActividadGrupal actividadGrupal)
	{
		if(actividadGrupal == null || actividadesGrupales.contains(actividadGrupal)==true) 
			return false;
		
	
		return actividadesGrupales.add(actividadGrupal);
	}
	
	/**
	 * aÃ±ade un plan a la lista de planes
	 * @param planPersonalizado plan a aÃ±adir
	 * @return true si se ha aÃ±adido correctamente, de lo contrario false
	 */
	public Boolean addPlanPersonalizado(PlanPersonalizado planPersonalizado)
	{
		if(planPersonalizado == null || planesPersonalizados.contains(planPersonalizado)==true)
			return false;
		
		return planesPersonalizados.add(planPersonalizado);
	}
	
	/**
	 * GETTER
	 * @return devuele el nombre
	 */
	public String getName()
	{
		return this.nombre;
	}

	/**
	 * GETTER
	 * @return devuelve el email
	 */
	public String getEmail()
	{
		return this.email;
	}
	
	/**
	 * GETTER
	 * @return devuelve las horas extra
	 */
	public Integer  getHorasExtra(){
		return this.horasExtra;
	}

	/**
	 * SETTER
	 * @param nombre nombre
	 * @return devuelve true si se ha puesto correctamente, de lo contrario false
	 */
	public Boolean setName(String nombre)
	{
		if(nombre == null)
			return false;

		this.nombre = nombre;
		return true;
	}

	/**
	 * SETTER
	 * @param email email
	 * @return devuelve true si se ha puesto correctamente, de lo contrario false
	 */
	public Boolean setEmail(String email)
	{
		if(email == null)
			return false;

		this.email = email;
		return true;
	}

	/**
	 * Comprueba si es administrador
	 * @return false
	 */
	@Override
    public boolean esAdministrador()
    {
    	return false;
    }
    
	/**
	 * Comprueba si es cliente
	 * @return false
	 */
    @Override
    public boolean esCliente()
    {
    	return false;
    }
    
    /**
     * Comprueba si es monitor
     * @return true
     */
    @Override
    public boolean esMonitor()
    {
    	return true;
    }
	
	
	/**
	 * descarga la nomina
	 * @param month mes
	 * @return devuelve true si se ha puesto correctamente, de lo contrario false
	 * @throws NonExistentFileException no hay excepciones
	 * @throws UnsupportedImageTypeException excepcion no soportada
	 * @throws InvalidPeriod periodo invalido
	 * @throws FechaPosterior  si la fecha es posterior
	 * @throws ExcepcionUsuario si el usuario no existe
     * @throws SueldoNoDefinido si el sueldo no ha sido definido
	 */
	public Boolean descargarNomina(Month month) throws NonExistentFileException,UnsupportedImageTypeException,InvalidPeriod, ExcepcionUsuario, FechaPosterior, SueldoNoDefinido
	{

		if(Gimnasio.getGimnasio()==null || month==null){
			return false;
		}
		
		if(Gimnasio.getGimnasio().getBaseSalaryPerMonth()<= 0.0) {
			throw new SueldoNoDefinido("Sueldo no definido.");
		}
		
		PayrollSystem.createPayroll(Gimnasio.getGimnasio(), this, month, Year.now(), this.horasExtra, "./resources/");
		return true;
	}
	
	/**
	 * calcula las horas extra
	 * @return devuelve true si se ha calculado correctamente, de lo contrario false
	 */
	public Boolean calcularHorasExtra()
	{
		
		for (ActividadGrupal actividad : actividadesGrupales){
			for(SesionMonitorizada sesion : actividad.getSesionesMonitorizadas()){
				this.horasExtra += sesion.calcularHorasSesion();
				
				return true;
			}
		}
		
		for (PlanPersonalizado plan : planesPersonalizados){
			for(SesionMonitorizada sesion : plan.getSesionesMonitorizadas()){
				this.horasExtra += sesion.calcularHorasSesion();
				
				return true;
			}
		}
		 return false;
		
	}
	
	/**
	 * Funcion para cancelar un plan personalizado
	 * @param actividad el plan a cancelar
	 * @return true si lo ha podido cancelar
	 */
	public Boolean cancelarPlanPersonalizado (Actividad actividad){
		Boolean status = false;
		List<SesionMonitorizada> toRemove = new ArrayList<>();

		if(actividad ==null){
			return false;
		}
		
		if(actividad.getSesionesMonitorizadas().size()>0){
			for(SesionMonitorizada sesion: actividad.getSesionesMonitorizadas()){
				toRemove.add(sesion);
			}
		}
		
		if (toRemove.size() > 0) {
			for (SesionMonitorizada sesion : toRemove) {
				status = this.cancelarSesionPlanPersonalizado(sesion);
			}
		}
		
		status = this.planesPersonalizados.remove(actividad);
		
		return status;
	}
	
	/**
	 * Funcion para cancelar una sesion personalizada
	 * @param sesion la sesion a cancelar
	 * @return true si se ha podido cancelar
	 */
	public Boolean cancelarSesionPlanPersonalizado(Sesion sesion){
		if(sesion == null){
			return false;
		}
		Notificacion not = null;
		for(PlanPersonalizado plan : this.planesPersonalizados){
			for(SesionMonitorizada sesionMonitorizada : plan.getSesionesMonitorizadas()){
				if(sesionMonitorizada.equals(sesion)){
					not = new Notificacion ("Se ha cancelado la sesion " + sesion.getActSesion().getNombre());
					try{ 
						//Mandamos notificacion a todos los clientes que hayan reservado esa sesion
						for(String nombre: Gimnasio.getGimnasio().getUsuarios().keySet()){
							if(Gimnasio.getGimnasio().getUsuarios().get(nombre) instanceof Cliente){
								for(int i = 0; i<((Cliente)Gimnasio.getGimnasio().getUsuarios().get(nombre)).getReservas().size(); i++){
									 if(((Cliente)Gimnasio.getGimnasio().getUsuarios().get(nombre)).getReservas().get(i).getSesion().equals(sesion)){
										Gimnasio.getGimnasio().getUsuarios().get(nombre).addNotificaciones(not);
										
										((Cliente)Gimnasio.getGimnasio().getUsuarios().get(nombre)).getReservas().remove(i);
										/* reembolso de la sesion */
										if (((Cliente)Gimnasio.getGimnasio().getUsuarios().get(nombre)).realizarPago((-1)*sesionMonitorizada.getPrecio())== false) {
											return false;
										}
										
									}
								}
							}
						}
						
					}catch(ExcepcionUsuario | FechaPosterior e){
						e.printStackTrace();
					}
					
					if(sesion.getSala() instanceof SalaSimple){
						SalaSimple miSala = (SalaSimple)sesion.getSala();
						miSala.removeSesion(sesion);
					}
					return plan.getSesionesMonitorizadas().remove(sesion);
				}
			}
		}
		return false;
	}

	/**
	 * coge el salario base del mes
	 * @return devuelve el sueldo del mes
	 * @throws FechaPosterior si la fecha es posterior
	 * @throws ExcepcionUsuario  si el usuario no existe
	 */
	public Double getBaseSalaryPerMonth() throws ExcepcionUsuario, FechaPosterior
	{

		if (Gimnasio.getGimnasio() == null)
			return -1.0;
		
		if(calcularHorasExtra() == false)
			return -1.0;
			
		Double total = (Gimnasio.getGimnasio().getBaseSalaryPerMonth() + (Gimnasio.getGimnasio().getRateHour()*this.horasExtra));
		
		if(total < 0.0)
			return -1.0;
		
		return total;
	}
	
	/**
	 * Funcion para indicar la averia de una maquina
	 * @param maquina la maquina averiada
	 * @return true si lo ha podido indicar
	 * @throws AveriaPreviamenteReportada si ya ha sido reportada la averia
	 */
	public Boolean indicarAveria(Maquina maquina) throws AveriaPreviamenteReportada{
		if (maquina == null){
			return false;
		}
		
		try {
			for(Maquina maq : Gimnasio.getGimnasio().getMaquinas()){
				if(maq.equals(maquina) && maquina.getEstado() != Estado.AVERIADA){
					maquina.setEstado(Estado.AVERIADA);
					return true;
				} else if (maq.equals(maquina) && maquina.getEstado() == Estado.AVERIADA){
					throw new AveriaPreviamenteReportada(maquina);
				}
			}
		} catch (ExcepcionUsuario | FechaPosterior e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
		
	}
	
	
	/**
	 * Funcion para mostrar el monitor
	 * @return devuelve la cadena
	 */
	@Override
	public String toString(){
		String cadena = super.toString();
		cadena += "\n";
		cadena += "(Monitor):\n";
		cadena += "Nombre: " + this.nombre + "\nNIF: "+ this.nif+ "\nCorreo electronico: " + this.email + "\nNº horas extra: " +this.horasExtra;
		return cadena;
	}

}
