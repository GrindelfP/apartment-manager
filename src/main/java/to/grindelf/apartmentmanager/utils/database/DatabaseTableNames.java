package to.grindelf.apartmentmanager.utils.database;

public enum DatabaseTableNames {

    USERS_TABLE {
        @Override
        public String toString() {
            return "users";
        }
    },

    APARTMENTS_TABLE {
        @Override
        public String toString() {
            return "apartments";
        }
    }
}
