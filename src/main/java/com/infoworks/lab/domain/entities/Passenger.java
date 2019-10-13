package com.infoworks.lab.domain.entities;

import com.it.soul.lab.sql.SQLExecutor;
import com.it.soul.lab.sql.entity.Ignore;
import com.it.soul.lab.sql.entity.PrimaryKey;
import com.it.soul.lab.sql.entity.TableName;
import com.it.soul.lab.sql.query.models.Property;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity(name = "Passenger")
@TableName(value = "Passenger")
public class Passenger extends com.it.soul.lab.sql.entity.Entity {
	
	@PrimaryKey(name="id", auto=true)
	@Id @Column(length = 100)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Integer id = 0;

    @NotNull(message = "Name must not be null")
    private String name;

    private Gender sex;

    @Min(value = 18, message = "Min Value is 18.")
	private int age = 18;


	@NotNull(message = "DOB Must Not Null") @Past(message = "Date Of Birth Must Be Greater Then Now")
    private Date dob = new Date();

	private boolean active;

	@Ignore
	private static int _autoIncriment = -1;

	public Passenger() {
	    this.id = ++_autoIncriment;
    }

    public Passenger(@NotNull(message = "Name must not be null") String name
            , Gender sex
            , @Min(value = 18, message = "Min Value is 18.") int age) {
        this();
	    this.name = name;
        this.sex = sex;
        this.age = age;
        updateDOB(age, false);
    }

    private void updateDOB(@Min(value = 18, message = "Min Value is 18.") int age, boolean isPositive) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Objects.nonNull(getDob()) ? getDob() : new Date());
        int year = calendar.get(Calendar.YEAR) - ((isPositive) ? -age : age);
        calendar.set(Calendar.YEAR, year);
        setDob(calendar.getTime());
    }

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Passenger passenger = (Passenger) o;
		return Objects.equals(id, passenger.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Property getPropertyTest(String key, SQLExecutor exe, boolean skipPrimary) {
		return getProperty(key, exe, skipPrimary);

	}

}
