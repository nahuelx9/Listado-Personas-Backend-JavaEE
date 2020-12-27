package com.np.data;

import com.np.domain.Persona;
import java.util.List;

public interface PersonaDao {

    public List<Persona> encontrarTodasPersonas();

    public Persona encontrarPersona(Persona persona);

    public void insertarPersona(Persona persona);

    public void actualizarPersona(Persona persona);

    public void eliminarPersona(Persona persona);

}
