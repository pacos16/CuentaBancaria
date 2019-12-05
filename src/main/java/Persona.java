import java.util.Random;

public class Persona implements Runnable{


    String nombre;
    Cuenta cuenta;
    Thread hilo;
    public Persona(String nombre, Cuenta cuenta){
        this.nombre=nombre;
        this.cuenta=cuenta;
        hilo=new Thread(this);
        hilo.start();
    }

    public void run(){
        Random random=new Random();
        for(int i=0;i<100;i++) {
            try{
                Thread.sleep(100);
            }catch (InterruptedException ie){

            }
            synchronized (cuenta) {
                boolean resultado = false;
                int r=random.nextInt(200);
                switch (random.nextInt(2)) {
                    case 0:
                        resultado = cuenta.ingresar(r, nombre);
                        if (!resultado) {
                            try {
                                cuenta.wait();
                                cuenta.ingresar(r, nombre);

                            } catch (InterruptedException ie) {

                            }
                        }
                        break;
                    case 1:
                        resultado = cuenta.retirar(random.nextInt(200), nombre);
                        if (!resultado) {
                            try {
                                cuenta.wait();
                                cuenta.retirar(random.nextInt(200), nombre);
                            } catch (InterruptedException ie) {

                            }
                        }
                        break;
                    default:
                        System.out.println("lolaso");
                }

            }
        }
    }
}
