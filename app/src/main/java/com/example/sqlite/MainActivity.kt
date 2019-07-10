package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var no:String =""
    var nom:String=""
    var edad:String="0"
    var carrera:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buscarEstudiante (v:View){
        if(etcontrol.text.isEmpty()){
            Toast.makeText(this,"Campos vacios",Toast.LENGTH_LONG).show()
        }
        else
        {
            no = etcontrol.text.toString()
            val admin = adBD(this)
                                                        //0      //1     //2       //3
            val tupla= admin.consulta("SELECT noControl, nomEst, carrera, edadEst FROM Estudiante WHERE noControl='$no'")

            if (tupla!!.moveToFirst())
            {
                etnombre.setText(tupla.getString(1))
                etcarrera.setText(tupla.getString(2))
                etedad.setText(tupla.getString(3))
                btnagregar.isEnabled=false
                btneliminar.isEnabled=true
                btnmodificar.isEnabled=true
            }
            else
                {
                    Toast.makeText(this, "No existe el nuemero de control", Toast.LENGTH_SHORT).show();
                    etcontrol.requestFocus()
                }
        }
    }
    fun agregaEstudiante(v: View){
        if (etcontrol.text.isEmpty() || etnombre.text.isEmpty()||etcarrera.text.isEmpty()||etedad.text.isEmpty()){
            Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();
        }
        else
        {
            leercajas()

            val sentecia :String="INSERT INTO Estudiante(noControl, nomEst, carrera, edadEst)VALUES('$no','$nom','$carrera',$edad)"
            val admin = adBD(this)

            if(admin.Ejecuta(sentecia) ==1){
                Toast.makeText(this, "Estudiante agregado", Toast.LENGTH_SHORT).show();
                limpiarcaja()
            }else
            {
                Toast.makeText(this, "Estudiante no agregado", Toast.LENGTH_SHORT).show();
                etcontrol.requestFocus()
            }
        }
    }

    fun modificarEstudiante(v:View){
        if (etcontrol.text.isEmpty() || etnombre.text.isEmpty()||etcarrera.text.isEmpty()||etedad.text.isEmpty()){
            Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();
        }
        else
        {
            leercajas()

            val sentecia :String="UPDATE Estudiante SET nomEst ='$nom', carrera='$carrera', edadEst=$edad WHERE noControl='$no'"
            val admin = adBD(this)

            if(admin.Ejecuta(sentecia) ==1){
                Toast.makeText(this, "Estudiante modificado", Toast.LENGTH_SHORT).show();
                limpiarcaja()
            }else
            {
                Toast.makeText(this, "Estudiante no modificado", Toast.LENGTH_SHORT).show();
                etcontrol.requestFocus()
            }
        }

    }

    fun elimaEstudiante(v:View){
        if (etcontrol.text.isEmpty()){
            Toast.makeText(this, "Insertar numero de control", Toast.LENGTH_SHORT).show();
        }
        else
        {
            leercajas()
            val sentecia :String="DELETE FROM Estudiante where noControl='$no'"
            val admin = adBD(this)

            if(admin.Ejecuta(sentecia) ==1){
                Toast.makeText(this, "Estudiante eliminado", Toast.LENGTH_SHORT).show();
                limpiarcaja()
            }else
            {
                Toast.makeText(this, "Estudiante no eliminado", Toast.LENGTH_SHORT).show();
                etcontrol.requestFocus()
            }

        }
    }

    //Asies jsjsjsjsjsjsjsjs

    fun leercajas(){
        no=etcontrol.text.toString()
        nom=etnombre.text.toString()
        carrera=etcarrera.text.toString()
        edad=etedad.text.toString()
    }
    fun limpiarcaja(){
        no=""
        nom=""
        carrera=""
        edad="0"
        etcontrol.setText("")
        etnombre.setText("")
        etcarrera.setText("")
        etedad.setText("0")
        btnagregar.isEnabled=true
        btnmodificar.isEnabled=false
        btneliminar.isEnabled=false
    }
}
