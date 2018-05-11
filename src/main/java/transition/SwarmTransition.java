package transition;

import vo.Swarm;

public class SwarmTransition implements LongTransition<Swarm[]> {


    @Override
    public void setDefaultValue(String value) {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public void neighbor(Swarm[] current, Swarm[] empty) {

    }

    @Override
    public Swarm[] next() {
        return null;
    }
}
