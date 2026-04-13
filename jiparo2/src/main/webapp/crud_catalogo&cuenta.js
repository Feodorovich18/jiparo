/*******************************************
 * AGREGAR CUENTA
 ******************************************/

const formulario = document.getElementById('form_create_cuenta');

const btnSubmit = document.getElementById('btn_create_cuenta');

formulario.addEventListener('submit', async function(event){
    event.preventDefault();

    const codigo_rubro = document.getElementById('codigo_rubro').value;
    const denominacion_rubro = document.getElementById('denominacion_rubro').value;

    const codigo_cuenta = document.getElementById('codigo_cuenta').value;
    const denominacion_cuenta = document.getElementById('denominacion_cuenta').value;

    const codigo_subcuenta = document.getElementById('codigo_subcuenta').value;
    const denominacion_subcuenta = document.getElementById('denominacion_subcuenta').value;

    const codigo_subsubcuenta = document.getElementById('codigo_subsubcuenta').value;
    const denominacion_subsubcuenta = document.getElementById('denominacion_subsubcuenta').value;



    const datosCuenta = {
        codigo_rubro: codigo_rubro,
        denominacion_rubro: denominacion_rubro,

        codigo_cuenta: codigo_cuenta,
        denominacion_cuenta: denominacion_cuenta,

        codigo_subcuenta: codigo_subcuenta,
        denominacion_subcuenta: denominacion_subcuenta,

        codigo_subsubcuenta: codigo_subsubcuenta,
        denominacion_subsubcuenta: denominacion_subsubcuenta
    };

    console.log('Enviando al servidor:' + datosCuenta);

    try {
        // FETCH
        const response = await fetch('/jiparo2/api/catalogoServlet?action=crear_cuenta', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // Enviamos JSON
            },
            body: JSON.stringify(datosCuenta)  // Convertir objeto JS a texto JSON
        });


        // limpiar el formulario
        formulario.reset();

    } catch (error) {
        console.error('Error:', error);
        alert('Error al enviar los datos: ' + error.message);
    }

});