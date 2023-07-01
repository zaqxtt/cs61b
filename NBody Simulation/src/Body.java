package src;

public class Body {

    private static final double G = 6.67E-11;

    /**
     * 当前x位置
     */
    double xxPos;

    /**
     * 当前y的位置
     */
    double yyPos;

    /**
     * x方向的当前速度
     */
    double xxVel;

    /**
     * y方向当前速度
     */
    double yyVel;

    /**
     * 质量
     */
    double mass;

    /**
     * 与描绘身体的图像对应的文件名
     */
    String imgFileName;

    public Body(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Body(Body body) {
        this.xxPos = body.xxPos;
        this.yyPos = body.yyPos;
        this.xxVel = body.xxVel;
        this.yyVel = body.yyVel;
        this.mass = body.mass;
        this.imgFileName = body.imgFileName;
    }

    /**
     * 计算两个body距离
     *
     * @param body
     * @return
     */
    public double calcDistance(Body body) {
        double dx = Math.abs(this.xxPos - body.xxPos);
        double dy = Math.abs(this.yyPos - body.yyPos);
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 计算给定Body对该Body施加的力
     *
     * @param body 给定物体
     * @return
     */
    public double calcForceExertedBy(Body body) {
        double r = calcDistance(body);
        return (G * this.mass * body.mass) / (r * r);
    }

    /**
     * 所有Body对当前Body施加的净 X
     *
     * @param bodies 所有Body的数组
     * @return
     */
    public double calcForceExertedByX(Body[] bodies) {
        double fxSum = 0;
        for (Body body : bodies) {
            if (body.equals(this)) {
                continue;
            }
            double dx = body.xxPos - this.xxPos;
            double f = calcForceExertedBy(body);
            double r = calcDistance(body);
            fxSum += f * dx / r;
        }
        return fxSum;
    }

    /**
     * 所有Body对当前Body施加的净 Y
     *
     * @param bodies 所有Body的数组
     * @return
     */
    public double calcForceExertedByY(Body[] bodies) {
        double fxSum = 0;
        for (Body body : bodies) {
            if (body.equals(this)) {
                continue;
            }
            double dy = body.yyPos - this.yyPos;
            double f = calcForceExertedBy(body);
            double r = calcDistance(body);
            fxSum += f * dy / r;
        }
        return fxSum;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

    /**
     * 更新Body的位置和速度实例
     *
     * @param dt 时间
     * @param fX x方向的力
     * @param fY y方向的的力
     */
    public void update(double dt, double fX, double fY) {
        //x方向
        // 加速度
        double ax = fX / this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.xxPos += dt * this.xxVel;

        double ay = fY / this.mass;
        this.yyVel += dt * ay;
        this.yyPos += dt * yyVel;
    }

    public double getXxPos() {
        return xxPos;
    }

    public void setXxPos(double xxPos) {
        this.xxPos = xxPos;
    }

    public double getYyPos() {
        return yyPos;
    }

    public void setYyPos(double yyPos) {
        this.yyPos = yyPos;
    }

    public double getXxVel() {
        return xxVel;
    }

    public void setXxVel(double xxVel) {
        this.xxVel = xxVel;
    }

    public double getYyVel() {
        return yyVel;
    }

    public void setYyVel(double yyVel) {
        this.yyVel = yyVel;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    @Override
    public String toString() {
        return "src.Body{" +
                "xxPos=" + xxPos +
                ", yyPos=" + yyPos +
                ", xxVel=" + xxVel +
                ", yyVel=" + yyVel +
                ", mass=" + mass +
                ", imgFileName='" + imgFileName + '\'' +
                '}';
    }
}
