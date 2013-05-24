package br.net.woodstock.test.seamtest.web;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Conversational;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.transaction.Transaction;

import br.net.woodstock.test.seamtest.orm.Contact;

@Name("contactBean")
@Conversational
public class ContactBean implements Serializable {

	private static final long	serialVersionUID	= -9092313603903257228L;

	@In
	private EntityManager		entityManager;

	private Contact				contact;

	private List<Contact>		contacts;

	public ContactBean() {
		super();
	}

	// Seam
	@Create
	public void create() {
		this.contact = new Contact();
		this.contacts = new ArrayList<Contact>();
	}

	@Begin
	@SuppressWarnings("unchecked")
	public String begin() throws Exception {
		this.contacts = this.entityManager.createQuery("SELECT c FROM Contact AS c ORDER BY c.name").getResultList();
		return "/contact.xhtml";
	}

	// Page
	public String add() {
		this.entityManager.persist(this.contact);
		this.contacts.add(this.contact);
		this.contact = new Contact();

		Collections.sort(this.contacts);

		return null;
	}

	@End
	public String persist() throws Exception {
		return "/index.xhtml";
	}

	@End
	public String cancel() throws Exception {
		Transaction.instance().setRollbackOnly();
		return "/index.xhtml";
	}

	// Getters
	public Contact getContact() {
		return this.contact;
	}

	public void setContact(final Contact contact) {
		this.contact = contact;
	}

	public List<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(final List<Contact> contacts) {
		this.contacts = contacts;
	}

	// Connection Info
	public String getConnectionInfo() {
		final EntityManager entityManager = this.entityManager;
		final Session session = (Session) entityManager.getDelegate();
		final StringBuilder builder = new StringBuilder();

		session.doWork(new Work() {

			public void execute(final Connection connection) throws SQLException {
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select pg_backend_pid()");

				rs.next();

				int pid = rs.getInt(1);

				builder.append("EntityManager Hash Code: " + entityManager.hashCode());
				builder.append(", Session Hash Code: " + session.hashCode());
				builder.append(", Connection Hash Code: " + connection.hashCode());
				builder.append(", URL: " + connection.getMetaData().getURL());
				builder.append(", Session PID: " + pid);
			}
		});

		return builder.toString();
	}

}
