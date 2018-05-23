package kvr.bookit;

/**
 * Created by Kasi_Visu on 4/8/2018.
 */

public class Restaurant {

    //Private Variable
    int _id;
    int _cus_no;
    String _name;
    String _phone_number;

    //empty constructor
    public Restaurant() {
    }

    //all parameter in Constructor
    public Restaurant(int _id, String _name, int _cus_no, String _phone_number) {
        this._id = _id;
        this._name = _name;
        this._cus_no = _cus_no;
        this._phone_number = _phone_number;
    }

    //three parameter Constructor
    public Restaurant(int _cus_no, String _name, String _phone_number) {
        this._cus_no = _cus_no;
        this._name = _name;
        this._phone_number = _phone_number;
    }


    //Getters for  all fields


    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public int get_cus_no() {
        return _cus_no;
    }

    public String get_phone_number() {
        return _phone_number;
    }

    //Setters for all fields
    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_cus_no(int _cus_no) {
        this._cus_no = _cus_no;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }
}
