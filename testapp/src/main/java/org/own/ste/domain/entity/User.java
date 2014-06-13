/**
 * 
 */
package org.own.ste.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.own.ste.admin.constants.RoleType;



/**
 * A user class.
 * 
 * @author Karthik.
 * 
 */
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "IISN")
    private String iisn;
    
	@Column(name = "LAST_LOGIN_TIME", nullable = true)
    private Date lastLoginTime;

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;


    /**
     * @param roleType String - The roletype.
     * @return boolean - True if has the role.
     */
    public boolean hasRole(RoleType roleType) {

        for(UserRole userRole: userRoles) {
            if(userRole.getId().equals(roleType.ordinal())) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return id Integer - Get the field.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            Integer - Set the field id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return userId - get the user id. 
     */
    public String getUserId() {
		return userId;
	}

	/**
	 * @param userId - set the password
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return iisn - get the IISN.
	 */
	public String getIisn() {
		return iisn;
	}

	/**
	 * @param iisn - set the iisn
	 */
	public void setIisn(String iisn) {
		this.iisn = iisn;
	}

    /**
     * @return lastLoginTime
     */
    public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime - set Last Login Time
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
     * @return userRoles List<UserRole> - Get the field.
     */
    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    /**
     * @param userRoles
     *            List<UserRole> - Set the field userRoles.
     */
    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

}
