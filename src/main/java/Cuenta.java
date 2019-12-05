public class Cuenta {

    private int saldo;
    private final int SALDO_MIN=0;
    private final int SALDO_MAX=2000;
    private final int BIG_INT=99999999;
    private int pendienteIngresar;
    private int pendienteSacar;
    public Cuenta(){
        saldo=1000;
        pendienteIngresar=BIG_INT;
        pendienteSacar=BIG_INT;
    }

    public boolean ingresar(int cantidad,String nombre){

        if((saldo+cantidad)>SALDO_MAX){
            System.out.println(nombre+" ha intentado ingresar "+cantidad+", va a esperar a una retirada de "+(cantidad+saldo-SALDO_MAX));
            if(pendienteSacar>(cantidad+saldo-SALDO_MAX)){
                pendienteSacar=cantidad+saldo-SALDO_MAX;
            }
            return false;
        }
        saldo+=cantidad;
        pendienteIngresar-=cantidad;
        pendienteSacar+=cantidad;
        if(pendienteIngresar<=0) {
            notifyAll();
            pendienteIngresar=BIG_INT;
        }
        System.out.println(nombre+" ingresa "+cantidad+" saldo total ="+saldo);
        return true;
    }
    public boolean retirar(int cantidad,String nombre){

        if((saldo-cantidad)<SALDO_MIN){
            System.out.println(nombre+" ha intentado retirar "+cantidad+", va a esperar a un ingreso de "+(cantidad-saldo));
            if(pendienteIngresar>(cantidad-saldo)){
                pendienteIngresar=cantidad-saldo;
            }
            return false;
        }
        saldo-=cantidad;
        pendienteSacar-=cantidad;
        pendienteIngresar+=cantidad;
        if(pendienteSacar<=0){
            notifyAll();
            pendienteSacar=BIG_INT;

        }

        System.out.println(nombre+" retira "+cantidad+" saldo total ="+saldo);

        return true;
    }
}
