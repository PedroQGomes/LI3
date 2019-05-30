package com.grupo19;

public class Client implements IClient {
    private String codigo;

    public Client(){
        this.codigo = "";
    }

    public Client(String s){
        this.codigo = s;
    }

    public Client(Client a){
        this.codigo = a.getCodigo();
    }

    public String getCodigo(){
        return this.codigo;
    }

    public Client clone(){
        return new Client(this);
    }

    public boolean equals(Object o){
        if(this == o)return true;
        if(o == null || this.getClass() != o.getClass())return false;
        Client p = (Client) o;
        return (this.codigo.equals(p));

    }

   

}
