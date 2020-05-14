/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clobi.transporte.controller;

import com.clobi.transporte.controller.util.Detalles;
import com.clobi.transporte.controller.util.Enums;
import com.clobi.transporte.controller.util.JsfUtil;
import com.clobi.transporte.controller.util.JsfUtil.PersistAction;
import com.clobi.transporte.controller.util.PagosExtras;
import com.clobi.transporte.entity.ActividadDiaria;
import com.clobi.transporte.entity.Anticipo;
import com.clobi.transporte.entity.Asignacion;
import com.clobi.transporte.entity.Categoria;
import com.clobi.transporte.entity.Empleado;
import com.clobi.transporte.entity.OperacionUnidad;
import com.clobi.transporte.entity.Pago;
import com.clobi.transporte.entity.Unidad;
import com.clobi.transporte.facade.ADFacade;
import com.clobi.transporte.facade.AnticiposFacade;
import com.clobi.transporte.facade.AsignacionFacade;
import com.clobi.transporte.facade.CategoriasFacade;
import com.clobi.transporte.facade.EmpleadosFacade;
import com.clobi.transporte.facade.OperationFacade;
import com.clobi.transporte.facade.PagosFacade;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author Tinkpad PC
 */
@Named(value = "ADController")
@SessionScoped
public class ActividadDiariaController implements Serializable {

    private boolean activityStatus;
    private ActividadDiaria actividad; //Es la actividad Actual 
    private OperacionUnidad operacionSelected;
    private List<OperacionUnidad> listOperaciones;
    private List<ActividadDiaria> listADs;
    private Anticipo anticipoInsert;
    private BigDecimal totalAnticipos;
    private List<Asignacion> listAsignaciones;
    private List<Categoria> listCategorias;
    private Asignacion asignacionSelected;
    private Detalles detallesSelected;
    private List<Empleado> listMotoristaDisp;
    private List<Empleado> listAuxliarDisp;
    private Empleado motoristaSelected;
    private Empleado auxiliarSelected;
    private String extraPay;

    @EJB
    private ADFacade ejbADFacade;
    @EJB
    private EmpleadosFacade ejbEmpleadosFacade;
    @EJB
    private OperationFacade ejbOperacion;
    @EJB
    private AnticiposFacade ejbAnticipos;
    @EJB
    private AsignacionFacade ebjAsignacion;
    @EJB
    private CategoriasFacade ejbCategorias;
    @EJB
    private PagosFacade ebjPagos;

    @PostConstruct
    public void init() {
        extraPay = "";
        this.actividad = ejbADFacade.currentActivity();
        activityStatus = (actividad != null) ? true : false;
        if (activityStatus) {
            this.listOperaciones = ejbOperacion.listOperaciones(this.actividad);
        }

        this.listCategorias = ejbCategorias.listCategoriasAsc();
    }

    public void removeHelpder() {
        this.auxiliarSelected = null;
    }

    public void deadActivity() {
        //verificar que las que se abrieron este cerradas 
        this.actividad.setEstado(Enums.ESTADO_ACTIVIDAD.FINALIZADA);
        persistActivity(PersistAction.UPDATE, "Se finalizo la Actividad");
    }

    public void showAdvance(OperacionUnidad e) {
        this.operacionSelected = e;
        this.anticipoInsert = null;
        this.totalAnticipos = ejbAnticipos.totalAnticipos(e);
    }

    //Solo seria pra finaliza 
    public void showDetailsEnd(OperacionUnidad e) {
        this.operacionSelected = e;
        this.detallesSelected = new Detalles();
        this.detallesSelected.setViajesRealizados((int) e.getViajesrealizados());
        this.detallesSelected.setConteoAnterior(operacionSelected.getContadorold());
        this.detallesSelected.setAnticipos(ejbAnticipos.totalAnticipos(e));

        for (Categoria c : listCategorias) {
            Pago p = new Pago();
            p.setMonto(new BigDecimal(0.0));
            p.setIdcategoria(c);
            detallesSelected.getListPagos().add(p);
        }
    }

    //Este para los detalles
    public void showDetalis(OperacionUnidad e) {
        this.operacionSelected = e;
        this.detallesSelected = new Detalles();
        detallesSelected.setViajesRealizados(e.getViajesrealizados());
        detallesSelected.setConteoActual(e.getContador());
        detallesSelected.setConteoAnterior(e.getContadorold());
        detallesSelected.setListPagos(e.getPagoList());
        detallesSelected.realizarCalculos();
    }

    public void prepareActivity() {
        Date currentDay = new Date();
        this.actividad = new ActividadDiaria();
        this.actividad.setTotalviajes(0);
        this.actividad.setIngresototal(new BigDecimal(0.0));
        this.actividad.setFecha(currentDay);
        this.actividad.setEstado(Enums.ESTADO_ACTIVIDAD.EJECUCION);
    }

    public void prepareOperation() {
        this.motoristaSelected = null;
        this.auxiliarSelected = null;
        this.asignacionSelected = null;
        this.totalAnticipos = null;

        this.operacionSelected = new OperacionUnidad();
        this.operacionSelected.setEstado(Enums.ESTADO_ACTIVIDAD.EJECUCION);
        this.operacionSelected.setViajesrealizados((short) 0);
        this.operacionSelected.setContador(0);
        this.operacionSelected.setContadorold(0);
        this.operacionSelected.setIdactividaddiaria(this.actividad);
        this.operacionSelected.setPagoconductor(new BigDecimal(0.0));
        this.operacionSelected.setPagoauxiliar(new BigDecimal(0.0));
        this.operacionSelected.setIngreso(new BigDecimal(0.0));
        this.listAsignaciones = ebjAsignacion.unidadesDisponibles(actividad.getId());
        this.listMotoristaDisp = ejbEmpleadosFacade.EmpleadosDisponibles(Enums.TIPO_EMPLEADO.MOTORISTA, actividad.getId());
        this.listAuxliarDisp = ejbEmpleadosFacade.EmpleadosDisponibles(Enums.TIPO_EMPLEADO.AYUDANTE, actividad.getId());

    }

    public void registroAnterior(Asignacion a) {
        OperacionUnidad e = ejbOperacion.registroAnterior(a.getUnidad().getPlaca(), actividad.getFecha());
        int contAnteior = 0;
        this.totalAnticipos = BigDecimal.ZERO;
        if (e != null) {
            contAnteior = e.getContador();
            if (e.isAutocierre()) {
                this.totalAnticipos = ejbAnticipos.totalAnticipos(e);
            }
            operacionSelected.setContadorold(contAnteior);
        }
    }

    public void prepareAnticipo() {
        this.anticipoInsert = new Anticipo();
    }

    public void createAnticipo() {
        this.anticipoInsert.setHora(new Date());
        this.anticipoInsert.setIdoperacion(operacionSelected);
        persistAnticipo(PersistAction.CREATE, "Anticipo Registrado!");
    }

    public void createOperation() {
        this.operacionSelected.setPlaca(this.asignacionSelected.getUnidad());
        this.operacionSelected.setIdconductor(this.motoristaSelected);
        if (this.auxiliarSelected != null) {
            this.operacionSelected.setIdauxiliar(auxiliarSelected);
        }
        persistOperation(PersistAction.CREATE, "Operacion Exitosa");
    }

    public void persistOperation(PersistAction actionPersist, String infoResult) {
        try {
            if (actionPersist != PersistAction.DELETE) {

                OperacionUnidad aux = ejbOperacion.edit(operacionSelected);
                if (!totalAnticipos.equals(BigDecimal.ZERO)) {
                    Anticipo a = new Anticipo();
                    a.setAnticipo(totalAnticipos);
                    a.setIdoperacion(aux);
                    a.setHora(new Date());
                    a.setTipoanticipo((short) 2);
                    ejbAnticipos.edit(a);
                }
            }
            JsfUtil.addSuccessMessage(infoResult);
            this.listOperaciones = ejbOperacion.listOperaciones(this.actividad);
        } catch (Exception e) {
            System.out.print("Error..");
            JsfUtil.addErrorMessage("Error al registrar Operacion");
        }
        this.operacionSelected = null;
        this.totalAnticipos = null;
    }

    public void createActivity() {
        prepareActivity();
        persistActivity(PersistAction.CREATE, "Actividad Insertada");
    }

    private void persistActivity(PersistAction actionPersist, String infoResult) {
        try {
            if (actionPersist != PersistAction.DELETE) {
                this.actividad = ejbADFacade.edit(this.actividad);
            }
            JsfUtil.addSuccessMessage(infoResult);
            activityStatus = true;
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error en la Operacion 2");
        }
    }

    private void persistAnticipo(PersistAction actionPersist, String infoResult) {
        try {
            if (actionPersist != PersistAction.DELETE) {
                ejbAnticipos.edit(anticipoInsert);
            }
            JsfUtil.addSuccessMessage(infoResult);
            //Preguntar como recargar solo el componente que se altero en lugar de toda la lista 
            this.listOperaciones = ejbOperacion.listOperaciones(this.actividad);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error en la Operacion 2");
        }
        anticipoInsert = null;
    }

    //Transaction 
    public void finalizarOperacion() {
        this.operacionSelected.setViajesrealizados((short) this.detallesSelected.getViajesRealizados());
        this.operacionSelected.setContador(this.detallesSelected.getConteoActual());
        this.operacionSelected.setContadorold(this.detallesSelected.getConteoAnterior());
        this.operacionSelected.setEstado(Enums.ESTADO_ACTIVIDAD.FINALIZADA);
        this.operacionSelected.setIngreso(this.detallesSelected.getIngresoCalculado());
        this.operacionSelected.setPagoconductor(detallesSelected.getListPagos().get(2).getMonto());
        if (this.operacionSelected.getIdauxiliar() != null) {
            this.operacionSelected.setPagoauxiliar(detallesSelected.getListPagos().get(3).getMonto());
        }

        //Aqui necesito la transaccion 
        try {
            if (!this.extraPay.equals("") && !this.extraPay.equals("[]")) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
                List<PagosExtras> listExtraPayAux = objectMapper.readValue(this.extraPay, new TypeReference<List<PagosExtras>>() {
                });
                
                for(PagosExtras p : listExtraPayAux){
                    System.out.println("Descript: "+ p.getDescripcion() +" Monto: " + p.getMonto());
                }
            }
            
            ejbOperacion.edit(operacionSelected);
            for (Pago p : detallesSelected.getListPagos()) {
                p.setIdoperacion(operacionSelected);
                ebjPagos.edit(p);
            }
            JsfUtil.addSuccessMessage("Operacion Finalizada");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("No se pudo finalizar la operacion");
        }
    }

    public void calcularDetalles() {
        this.detallesSelected.realizarCalculos();
    }

    public Anticipo getAnticipoInsert() {
        return anticipoInsert;
    }

    public void setAnticipoInsert(Anticipo anticipoInsert) {
        this.anticipoInsert = anticipoInsert;
    }

    public boolean isActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(boolean activityStatus) {
        this.activityStatus = activityStatus;
    }

    public OperacionUnidad getOperacionSelected() {
        return operacionSelected;
    }

    public void setOperacionSelected(OperacionUnidad operacionSelected) {
        this.operacionSelected = operacionSelected;
    }

    public List<OperacionUnidad> getListOperaciones() {
        return listOperaciones;
    }

    public void setListOperaciones(List<OperacionUnidad> listOperaciones) {
        this.listOperaciones = listOperaciones;
    }

    public BigDecimal getTotalAnticipos() {
        return totalAnticipos;
    }

    public void setTotalAnticipos(BigDecimal totalAnticipos) {
        this.totalAnticipos = totalAnticipos;
    }

    public List<Asignacion> getListAsignaciones() {
        return listAsignaciones;
    }

    public void setListAsignaciones(List<Asignacion> listAsignaciones) {
        this.listAsignaciones = listAsignaciones;
    }

    public Asignacion getAsignacionSelected() {
        return asignacionSelected;
    }

    public void setAsignacionSelected(Asignacion asignacionSelected) {
        registroAnterior(asignacionSelected);
        this.asignacionSelected = asignacionSelected;
        this.motoristaSelected = asignacionSelected.getMotorista();
        this.auxiliarSelected = asignacionSelected.getAyudante();
    }

    public Detalles getDetallesSelected() {
        return detallesSelected;
    }

    public void setDetallesSelected(Detalles detallesSelected) {
        this.detallesSelected = detallesSelected;
    }

    public ActividadDiaria getActividad() {
        return actividad;
    }

    public void setActividad(ActividadDiaria actividad) {
        System.out.print(actividad.toString());
        this.actividad = actividad;
    }

    public void testingA() {
        System.out.print("Hola mundo entre aqui");
    }

    public List<Categoria> getListCategorias() {
        return listCategorias;
    }

    public void setListCategorias(List<Categoria> listCategorias) {
        this.listCategorias = listCategorias;
    }

    public List<Empleado> getListMotoristaDisp() {
        return listMotoristaDisp;
    }

    public void setListMotoristaDisp(List<Empleado> listMotoristaDisp) {
        this.listMotoristaDisp = listMotoristaDisp;
    }

    public List<Empleado> getListAuxliarDisp() {
        return listAuxliarDisp;
    }

    public void setListAuxliarDisp(List<Empleado> listAuxliarDisp) {
        this.listAuxliarDisp = listAuxliarDisp;
    }

    public Empleado getMotoristaSelected() {
        return motoristaSelected;
    }

    public void setMotoristaSelected(Empleado motoristaSelected) {
        this.motoristaSelected = motoristaSelected;
    }

    public Empleado getAuxiliarSelected() {
        return auxiliarSelected;
    }

    public void setAuxiliarSelected(Empleado auxiliarSelected) {
        this.auxiliarSelected = auxiliarSelected;
    }

    public List<ActividadDiaria> getListADs() {
        this.listADs = ejbADFacade.findAll();
        return listADs;
    }

    public void setListADs(List<ActividadDiaria> listADs) {
        this.listADs = listADs;
    }

    public String getExtraPay() {
        return extraPay;
    }

    public void setExtraPay(String extraPay) {
        this.extraPay = extraPay;
    }

}
