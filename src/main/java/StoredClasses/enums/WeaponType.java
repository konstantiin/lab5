package StoredClasses.enums;

/**
 * stored class
 */
public enum WeaponType {
    PISTOL,
    SHOTGUN,
    RIFLE,
    MACHINE_GUN;
    public static WeaponType valueOf(int i){
        return WeaponType.values()[i-1];
    }
}