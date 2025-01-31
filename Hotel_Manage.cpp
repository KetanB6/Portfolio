#include<iostream>
#include<string>
#include<vector>
#include<algorithm>
#include<fstream>
#include<ctime>
using namespace std;
vector<int> rooms = {101, 102, 103, 104, 105, 106, 107};
int avail_rooms = rooms.size();

static int index2 = 0;
    
class hotel
{
    private :
    vector<string> customers;
    string c_Fname, c_Lname, c_mob, c_aadhaar;
    int room_no;

    public :
    string booking_time, cancel_time;
    int booking_time_int, cancel_time_int;
    int service_no;
    int stay;

    hotel(int service_no){
        this->service_no = service_no;
    }

    int getTime(){
        string getSec;
        time_t justTime = time(0);
        string currenTime = ctime(&justTime);
            for(int i = 17; i < 20; i++){
                getSec = getSec + currenTime[i];
            } 
        return stoi(getSec);
    }
    
    void service_check(){
        int index = 1;
        if(service_no == 1001){
            avl_room();
        }
        else if(service_no == 1002){
            new_book();
        }
        else if(service_no == 1003){
            cancel_time_int = getTime();
            cancel();
        }
        else if(service_no == 1004){
            depart();
        }
        else if(service_no == 1005){
            show_clients();
        }
        else if(service_no == 1006){
            cout<<endl<<"\t\t\tThank You!"<<endl;
            exit(0);
        }
        else{
            cout<<"Invalid service code!"<<endl<<endl;
        }
    }

    void avl_room(){
    int index = 1;
    sort(rooms.begin(), rooms.end(), greater<int>());
    cout<<"\nAvailable rooms are :"<<endl;
            for(int i = avail_rooms-1; i >= 0; --i){
                if(rooms[i] <= 102){
                    cout<<index<<". Room Number : "<<rooms[i]<<"[4 Beds]"<<endl;
                }
                else{
                    cout<<index<<". Room Number : "<<rooms[i]<<"[2 Beds]"<<endl;
                }
                index++;
            }
    }

    void new_book(){
        cout<<"Enter First Name   : ";
        cin>>c_Fname;
        cout<<"Enter Last Name    : ";
        cin>>c_Lname;
        repe:cout<<"Enter Mobile No.   : ";
        cin>>c_mob;
        if(c_mob.length() != 10){
            cout<<"Invalid mobile number!"<<endl; 
            goto repe;
        }
        renter:cout<<"Enter Aadhaar      : ";
        cin>>c_aadhaar;
        if(c_aadhaar.length() != 12){
            cout<<"Invalid Aadhaar!"<<endl; 
            goto renter;
        }
        cout<<"Enter staying days : ";
        cin>>stay;
        if(select_room() == 0){
            return;
        }
        cout<<"Congratulations! booking confirmed."<<endl;
        index2++;
        time_t now1 = time(0);
        booking_time = ctime(&now1);
        booking_time_int = getTime();
        receipt();
        ofstream out("client_list.txt", ios::app);
        if(out.is_open()){
        out<<room_no<<"\t\t"<<c_Fname<<" "<<c_Lname<<"  \t"<<c_mob<<"\t"<<c_aadhaar<<"   \t"<<booking_time;
        }
        customers.push_back(room_no+"\t"+c_Fname+" "+c_Lname+"\t"+c_mob+"\t"+c_aadhaar+"\t"+booking_time);
        out.close();
    }

    int select_room(){
        int room;
        avl_room();
        cout<<"Choose room from available rooms : ";
        cin>>room;
        bool isAvail = false;
        for(int i = 0; i < avail_rooms; i++){
            if(room == rooms[i]){
                room_no = room;
                isAvail = true;
                break;
            }
        }
        if(isAvail == false){
            cout<<"No such room available!"<<endl;
            return 0;
        }
        vector<int> :: iterator iter;   
        for(iter = rooms.begin(); iter != rooms.end(); iter++){
            if(*iter == room_no){
                rooms.erase(iter);
                avail_rooms = avail_rooms - 1;
                break;
            }
        }
        return 1;
    }

    void receipt(){
        int charges;
        if(room_no == 101 || room_no == 102){
            charges = 1100;
        }
        else{
            charges = 600;
        }
        cout<<"\n\t\t\t\t\t"<<"*Receipt"<<endl;
        cout<<"\t\t\t\t\t"<<"********Hotel Paradise*********"<<endl;
        cout<<"\t\t\t\t\t"<<"Date-time : "<<booking_time<<endl;
        cout<<"\t\t\t\t\t"<<"Name      : "<<c_Fname<<" "<<c_Lname<<endl;
        cout<<"\t\t\t\t\t"<<"Mobile    : "<<c_mob<<endl;
        cout<<"\t\t\t\t\t"<<"----------------------------------------------------------"<<endl;
        cout<<"\t\t\t\t\t"<<"Room No.\t\t"<<"Rent\t\t"<<"Days\t\t"<<"Total"<<endl<<endl;
        cout<<"\t\t\t\t\t"<<room_no<<"\t\t\t"<<charges<<"\t\t"<<stay<<"\t\t"<<charges*stay<<endl;
        cout<<"\t\t\t\t\t"<<"\t\t\t\t\t\t\tThank You!"<<endl;
        cout<<"\t\t\t\t\t"<<"*Cancelation should be avialable for 15 seconds after receiving receipt."<<endl;
    }

    void cancel(){
        cout<<"*Room cancellation"<<endl;
        int temp;//room number to cancel
        ofstream canc("client_list.txt", ios :: app);
        ifstream taker("client_list.txt");
        cout<<"Enter room number : ";
        cin>>temp;
        bool flag = validate_cancel();
        if(flag == true){
            return;
        }
        if(temp < 101 || temp > 107){
            cout<<"No such room exist!"<<endl;
            return;
        }
        for(int i = 0; i < avail_rooms; i++){
            if(temp == rooms[i]){
                cout<<"No such room booked!"<<endl;
                return;
            }
        }
        string getSec;
        time_t justTime = time(0);
        string currenTime = ctime(&justTime);
        string line;
        while(taker.eof() != 1){//scan the file until end of file is true
            getline(taker, line);
            if(line.find(to_string(temp)) == 0){//find the room number in taken line
                avail_rooms++;
                rooms.push_back(temp);
                canc<<line<<"   CANCELLED   "<<currenTime;
                cout<<"Booking for room number "<<temp<<" cancelled!"<<endl;
                break;
            }
        }
    }
    
    void depart(){
        cout<<"*Departure"<<endl;
        int temp;
        string line;
        ofstream canc("client_list.txt", ios :: app);
        ifstream taker("client_list.txt");
        cout<<"Enter room number : ";
        cin>>temp;
        if(temp < 101 || temp > 107){
            cout<<"No such room exist!"<<endl;
            return;
        }
        for(int i = 0; i < avail_rooms; i++){
            if(temp == rooms[i]){
                cout<<"No such room booked!"<<endl;
                return;
            }
        }
        while(taker.eof() != 1){
            getline(taker, line);
            if(line.find(to_string(temp)) == 0){
                avail_rooms++;
                rooms.push_back(temp);
                canc<<line<<" DEPARTED"<<endl;
                cout<<"Customers of room "<<temp<<" has departed!"<<endl;
                break;
            }
        }
    }

    void show_clients(){
        string lists;
        fstream final_take("client_list.txt");
        cout<<endl<<"Room No."<<"\t"<<"Name"<<"  \t\t"<<"Mobile No."<<"\t"<<"Aadhaar No."<<"   \t"<<"Date/ Time"<<"\t\t   "<<" Status"<<"     Cancel time"<<endl;
        while(final_take.eof() != 1){
        getline(final_take, lists);
        cout<<endl<<lists<<endl;
       }
    }

    bool validate_cancel()
    {
    int diff;
    if(cancel_time_int < 15 || cancel_time_int == 0){
        cancel_time += 60;
    }
    diff = cancel_time_int - booking_time_int;
    if(diff <= 15){
        return false;
    }
    else{
        cout<<"Cancellation time already expired!"<<endl;
        return true;
    }
    }
    
};

int main()
{
    int service;
    char conf;
    ofstream reset("client_list.txt", ios::trunc);
    cout<<endl<<"*********Paradise Lodge*********"<<endl<<endl;
    reception : cout<<"\n#. Reception/ Service Codes :"<<endl;
    cout<<"1001. Available rooms"<<endl;
    cout<<"1002. New booking"<<endl;
    cout<<"1003. Booking Cancellation"<<endl;
    cout<<"1004. Departure"<<endl;
    cout<<"1005. Assigned rooms list"<<endl;
    cout<<"1006. Exit"<<endl;
    cout<<"\t\t\t\t\tEnter service code : ";
    cin>>service;
    hotel manage(service); 
    manage.service_check();
    //manage.service_check();
    cout<<"\n\t\t\t\t\tWant any service further? Y/y : ";
    cin>>conf;
    if(conf == 'Y' || conf == 'y'){
        goto reception;
    }
    else{
        cout<<"\t\t\t\t\tThank you!"<<endl;
    }
    return 0;
}