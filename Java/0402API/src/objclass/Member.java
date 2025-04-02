package objclass;

public class Member {
    public String id;

    public Member(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof Member) {
            Member mbr = (Member) obj;
            if(this.id.equals(mbr.id)) {
                return true;
            }
        }
        return false;
    }
}
