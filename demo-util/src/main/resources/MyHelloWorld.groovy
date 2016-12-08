import com.ruzhi.demo.util.groovy.IMyHelloWorld

//http://my.oschina.net/joshuazhan/blog/137940
public class MyHelloWorld implements IMyHelloWorld{

    @Override
    public void run() {
        String var = "hello world---------------------ruzhi";
        System.out.println(var);
    }

}