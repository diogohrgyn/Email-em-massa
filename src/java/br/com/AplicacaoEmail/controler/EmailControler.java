/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.AplicacaoEmail.controler;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author Jccall00055
 */
public class EmailControler {

    private String titulo;
    private String corpo;
    private String beanuser;
    private String beansenha;
    private String logarusuario;
    private String logarsenha;
EmailControler emc = null;
    public EmailControler() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    /**
     *
     * @return
     */
    public String sair() {
       
        return "index";
    }
    public String enviar() {
        final String username = "cobranca@jcsf.com.br";
        final String senha = "125612";
        EmailControler emails = new EmailControler();
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(new File("C:\\Users\\Jccall00055\\Documents\\NetBeansProjects\\AplicacaoEmail_1\\web\\Remessa\\emailemmassa"+logarusuario+".xls"));
        } catch (IOException ex) {
            Logger.getLogger(EmailControler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(EmailControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        Sheet sheet = workbook.getSheet(0);
        int linhas = sheet.getRows();
        int colunas = sheet.getColumns();
        for (int i = 0; i < linhas; i++) {
            String email = (String) sheet.getCell(0, i).getContents();
            //mensagens = (String) sheet.getCell(1, 0).getContents();
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttle.enable", "true");
            prop.put("mail.smtp.host", "mail.jcsf.com.br");
            prop.put("mail.smtp.port", "587");

            Session session = Session.getInstance(prop, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(beanuser, beansenha); //To change body of generated methods, choose Tools | Templates.
                }
            });
            Message message = new MimeMessage(session);

            try {
                message.setFrom(new InternetAddress(beanuser));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject(this.titulo);
                message.setContent("<html><head></head><body>" + this.corpo + "</body></html>","text/html; charset=UTF-8");
                Transport.send(message);
            } catch (MessagingException ex) {
                Logger.getLogger(EmailControler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return "acesso";
    }

    public String getBeanuser() {
        return beanuser;
    }

    public void setBeanuser(String beanuser) {
        this.beanuser = beanuser;
    }

    public String getBeansenha() {
        return beansenha;
    }

    public void setBeansenha(String beansenha) {
        this.beansenha = beansenha;
    }

    /**
     *
     * @return
     */
    public String logar() {
        String acesso = "erro";
        try {
            List<EmailControler> usuarios = new LinkedList<>();
            EmailControler lb = new EmailControler();
            lb.setLogarusuario("bmginativo");
            lb.setLogarsenha("bmginativo");
            usuarios.add(lb);
            EmailControler lb_1 = new EmailControler();
            lb_1.setLogarusuario("bonsucesso");
            lb_1.setLogarsenha("bonsucesso");
            usuarios.add(lb_1);
            EmailControler lb_2 = new EmailControler();
            lb_2.setLogarusuario("bmgcard");
            lb_2.setLogarsenha("bmgcard");
            usuarios.add(lb_2);
            EmailControler lb_3 = new EmailControler();
            lb_3.setLogarusuario("bmgativo");
            lb_3.setLogarsenha("bmgativo");
            usuarios.add(lb_3);
            EmailControler lb_4 = new EmailControler();
            lb_4.setLogarusuario("novomundo");
            lb_4.setLogarsenha("novomundo");
            usuarios.add(lb_4);
            EmailControler lb_5 = new EmailControler();
            lb_5.setLogarusuario("daycoval");
            lb_5.setLogarsenha("daycoval");
            usuarios.add(lb_5);
            EmailControler lb_6 = new EmailControler();
            lb_6.setLogarusuario("itaubmg");
            lb_6.setLogarsenha("itaubmg");
            usuarios.add(lb_6);
            EmailControler lb_7 = new EmailControler();
            lb_7.setLogarusuario("bmgvendas");
            lb_7.setLogarsenha("bmgvendas");
            usuarios.add(lb_7);
            EmailControler lb_8 = new EmailControler();
            lb_7.setLogarusuario("intermedium");
            lb_7.setLogarsenha("intermedium");
            usuarios.add(lb_7);
            for (EmailControler usuario1 : usuarios) {
                if (usuario1.getLogarusuario().equals(logarusuario) && usuario1.getLogarsenha().equals(logarsenha)) {
                    acesso = "logado";
                }
            }
        } catch (Exception e) {
        }

        return acesso;
    }

    public String getLogarusuario() {
        return logarusuario;
    }

    public void setLogarusuario(String logarusuario) {
        this.logarusuario = logarusuario;
    }

    public String getLogarsenha() {
        return logarsenha;
    }

    public void setLogarsenha(String logarsenha) {
        this.logarsenha = logarsenha;
    }
}
