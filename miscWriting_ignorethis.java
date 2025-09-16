    static Position3dArrayBag union(Position3DArrayBag bag1, Position3DArrayBag bag2){
        position3D[] bag = new position3D[bag1.size()+bag2.size()];
        position3D[] hold = bag1.getBag();
        int count = 0
        for(int i=0; i<hold.length; i++){
            if(hold[i]!=null){
                bag[count]=hold[i];
                count++;
            }
        }
        hold = bag2.getBag();
        for(int i=0; i<hold.length; i++){
            if(hold[i]!=null){
                bag[count]=hold[i];
                count++;
            }
        }
        return bag.clone();
    }
