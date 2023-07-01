package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {

//    private static final String fname="./data/planets.txt";

    public static double readRadius(String fileName) {
        Scanner scan = null;
        try {
            scan = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 读取半径
        scan.nextInt();
        double v = scan.nextDouble();
        scan.close();
        return v;   // must return a double here
    }

    /**
     * 初始化 Body 对象数组。
     *
     * @param fname 文件名
     * @return Body数组
     * @throws FileNotFoundException
     */
    public static Body[] readBodies(String fname) throws FileNotFoundException {

        Scanner s = new Scanner(new File(fname));

        int nb = s.nextInt();
        s.nextDouble();
        Body[] bodies = new Body[nb];

        for (int k = 0; k < nb; k++) {
            String bodyStr = s.next();
            Body body = new Body(Double.parseDouble(bodyStr), Double.parseDouble(s.next()),
                    Double.parseDouble(s.next()), Double.parseDouble(s.next()), Double.parseDouble(s.next()), s.next());
            bodies[k] = body;
        }

        s.close();

        return bodies;
    }

    public static void main(String[] args) {
        double totalTime = 157788000.0;
        double dt = 25000.0;

        String fname = "./data/planets.txt";
        if (args.length > 2) {
            totalTime = Double.parseDouble(args[0]);
            dt = Double.parseDouble(args[1]);
            fname = args[2];
        }

        Body[] bodies = new Body[0];
        try {
            bodies = readBodies(fname);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        double radius = readRadius(fname);

        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for (double t = 0.0; t < totalTime; t += dt) {

            // to hold forces on each body
            double[] xforces = new double[bodies.length];
            double[] yforces = new double[bodies.length];

            // net forces and store in xforces and yforces
            for (int i = 0; i < bodies.length; i++) {
                double xforce = bodies[i].calcForceExertedByX(bodies);
                double yforce = bodies[i].calcForceExertedByY(bodies);
                xforces[i] = xforce;
                yforces[i] = yforce;
            }


            // with dt and corresponding xforces, yforces values
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xforces[i], yforces[i]);
            }


            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Body body : bodies) {
                body.draw();
            }

            StdDraw.show(10);
        }

        // prints final values after simulation

        System.out.printf("%d\n", bodies.length);
        System.out.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].getXxPos(), bodies[i].getYyPos(),
                    bodies[i].getXxVel(), bodies[i].getYyVel(),
                    bodies[i].getMass(), bodies[i].getImgFileName());
        }
    }
}
