package br.com.digital.sorte.login.data.dto;

public enum PermissionEnum {


    ADMIN(1, "Administrador"), MANAGER(2, "Gestor"), USER_COMMON(3, "Usuário");

    private int code;
    private String description;

    private PermissionEnum(int code, String description){
        this.code = code;
        this.description = description;
    }

    public static PermissionEnum getPermissionEnumByCode(int code){
        for(PermissionEnum premission : PermissionEnum.values()) {
			if(premission.getCode() == code) {
				return premission;
			}
		}
		return null;
    }

    public static PermissionEnum getStatusByDescricao(String description) {
		for(PermissionEnum permission : PermissionEnum.values()) {
			if(permission.getDescription() .equals(description)) {
				return permission;
			}
		}
		return null;
	}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

}
