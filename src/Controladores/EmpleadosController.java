package Controladores;

import Modelos.*;
import Vistas.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author umg
 */
public class EmpleadosController implements ActionListener, MouseListener{
 frmEmpleados VistaEmpleados;
 frmPrincipal VistaPrincipal;
 EmpleadosModel ModeloEmpleado;
 
    

    public EmpleadosController(frmEmpleados VistaEmpleados, frmPrincipal VistaPrincipal, EmpleadosModel ModeloEmpleado) {
        this.VistaEmpleados = VistaEmpleados;
        this.VistaPrincipal = VistaPrincipal;
        this.ModeloEmpleado = ModeloEmpleado;
        
        /*LEVANTAR LAS VISTAS*/
      this.VistaPrincipal.setExtendedState(frmPrincipal.MAXIMIZED_BOTH);
      this.VistaPrincipal.setVisible(true);
      
      /*PONER A LA ESCUCHA LOS BOTONES*/
      this.VistaEmpleados.btn_Agregar.addActionListener(this);
      this.VistaEmpleados.btn_Editar.addActionListener(this);
      this.VistaEmpleados.btnEliminar.addActionListener(this);
        
      /*REALIZAR LA CONEXION*/
            
            //Limpiar la tabla Vista Empleados
                DefaultTableModel TablaModelo = new DefaultTableModel();
                TablaModelo.setRowCount(0);
                TablaModelo.setColumnCount(0);
                this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
      
            //prepara el modelo de la tabla
                    TablaModelo.addColumn("IDEMPLEADO");
                    TablaModelo.addColumn("APELLIDOS");
                    TablaModelo.addColumn("NOMBRE");
                    TablaModelo.addColumn("TELEFONO");
                    
      /* LEVANTAR EL MODELO Y LOGRAR RECORRER EL RESULTSET*/
        //Captar el resultado que viene del Modelo desde el método LISTARDATOS
                ResultSet rstEmpleados =  this.ModeloEmpleado.ListarDatos();
               
                    try
                    {
                       
                    while(rstEmpleados.next())
                    {
                     TablaModelo.addRow(new Object[]{rstEmpleados.getInt("idEmpleado"),rstEmpleados.getString("Apellidos"),rstEmpleados.getString("Nombre"),rstEmpleados.getString("Telefono")});  
                    }  
                    }
                    catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(null, "Algo hizo falta..."+e);
                    }
                   
            //PASAR EL MODELO CREADO A LA TABLA DE LA VISTA EMPLEADOS        
                    this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
                    
        //PONER A LA ESCUCHA LA TABLA EMPLEADOS
        this.VistaEmpleados.jtbEmpleados.addMouseListener(this);
        
        /*LEVANTAR LA VISTA EMPLEADOR*/
      this.VistaEmpleados.setLocationRelativeTo(null);
      this.VistaEmpleados.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.VistaEmpleados.btn_Editar)
        {
            this.ModeloEmpleado.Actualizar(Integer.parseInt(this.VistaEmpleados.txtCodigo.getText()), this.VistaEmpleados.txtApellidos.getText(), this.VistaEmpleados.txtNombre.getText(), this.VistaEmpleados.txtTelefono.getText());
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        int fila;
        if(arg0.getSource()==this.VistaEmpleados.jtbEmpleados){
            fila = this.VistaEmpleados.jtbEmpleados.getSelectedRow();
            this.VistaEmpleados.txtCodigo.setText(this.VistaEmpleados.jtbEmpleados.getValueAt(fila, 0).toString());
            
            this.VistaEmpleados.txtApellidos.setText(this.VistaEmpleados.jtbEmpleados.getValueAt(fila, 1).toString());
            
            this.VistaEmpleados.txtNombre.setText(this.VistaEmpleados.jtbEmpleados.getValueAt(fila, 2).toString());
            
            this.VistaEmpleados.txtTelefono.setText(this.VistaEmpleados.jtbEmpleados.getValueAt(fila, 3).toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    } 
}
