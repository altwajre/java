package com.company.app;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class App
{
    static class Server{
        private String name;
        private String ip;
        public Server(String name, String ip){
            this.name = name;
            this.ip = ip;
        }
        public String getName() { return name; }
        public String getIp() { return ip; }
    }
    static final class LoadBalancer{
        private static final LoadBalancer _instance = new LoadBalancer();
        private List<Server> _servers;
        private Random _random = new Random();
        private LoadBalancer(){
            _servers = new ArrayList<Server>();
            _servers.add(new Server("ServerI", "120.14.220.18"));
            _servers.add(new Server("ServerII", "120.14.220.19"));
            _servers.add(new Server("ServerIII", "120.14.220.20"));
            _servers.add(new Server("ServerIV", "120.14.220.21"));
            _servers.add(new Server("ServerV", "120.14.220.22"));
        }
        public static LoadBalancer getLoadBalancer(){ return _instance; }
        public Server NextServer(){
            int r = _random.nextInt(_servers.size());
            return _servers.get(r);
        }
    }
    public static void main( String[] args )
    {
        LoadBalancer b1 = LoadBalancer.getLoadBalancer();
        LoadBalancer b2 = LoadBalancer.getLoadBalancer();
        LoadBalancer b3 = LoadBalancer.getLoadBalancer();
        LoadBalancer b4 = LoadBalancer.getLoadBalancer();
        if(b1 == b2 && b2 == b3 && b3 == b4){
            System.out.println("Same instance\n");
        }
        LoadBalancer balancer = LoadBalancer.getLoadBalancer();
        for(int i = 0; i < 8; i++){
            String serverName = balancer.NextServer().getName();
            System.out.println("Dispatch request to: " + serverName);
        }
    }
}
