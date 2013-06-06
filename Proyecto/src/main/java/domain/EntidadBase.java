package domain;

public interface EntidadBase  <PK extends Number> {
	PK getId();
	void setId(PK id);
}