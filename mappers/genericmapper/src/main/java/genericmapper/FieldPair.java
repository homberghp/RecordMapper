package genericmapper;

import java.util.Objects;

/**
 * Simple key object pair to help mappings. Candidate record from Java
 * {@code >= 16}.
 *
 * This fieldpair's hashCode and equals only consider the key, and ignore the
 * value in the computation, so be aware.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public record FieldPair(String key, Object value) {

    /**
     * Hash code based on key only.
     *
     * @return hasCode of this FieldPair.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode( this.key );
        return hash;
    }

    /**
     * Equals based on key only
     *
     * @param obj other
     * @return true if this.key==other.key
     */
    @Override
    public boolean equals(Object obj) {
        if ( obj instanceof FieldPair fp ) {
            return Objects.equals( this.key, fp.key );
        }
        return false;
    }
}
