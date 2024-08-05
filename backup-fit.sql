PGDMP                       |            fit    16.3    16.3 .    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16397    fit    DATABASE     z   CREATE DATABASE fit WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE fit;
                postgres    false            �            1255    16447 G   add_user(character varying, character varying, character varying, date) 	   PROCEDURE     0  CREATE PROCEDURE public.add_user(IN p_name character varying, IN p_email character varying, IN p_password character varying, IN p_birthdate date)
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO users (name, email, password, birthdate)
    VALUES (p_name, p_email, p_password, p_birthdate);
END;
$$;
 �   DROP PROCEDURE public.add_user(IN p_name character varying, IN p_email character varying, IN p_password character varying, IN p_birthdate date);
       public          postgres    false            �            1255    16460 G   addactivity(bigint, character varying, integer, integer, date, boolean) 	   PROCEDURE     �  CREATE PROCEDURE public.addactivity(IN user_id bigint, IN activity_type character varying, IN duration integer, IN calories integer, IN activity_date date, IN completed boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO activities (userid, type, duration, calories, date, completed)
    VALUES (user_id, activity_type, duration, calories, activity_date, completed);
END;
$$;
 �   DROP PROCEDURE public.addactivity(IN user_id bigint, IN activity_type character varying, IN duration integer, IN calories integer, IN activity_date date, IN completed boolean);
       public          postgres    false            �            1255    16455 7   addgoal(bigint, character varying, integer, date, date) 	   PROCEDURE     `  CREATE PROCEDURE public.addgoal(IN user_id bigint, IN goal_type character varying, IN goal_value integer, IN goal_period date, IN start_date date)
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO goals (userid, goaltype, goalvalue, goalperiod, start, completed)
    VALUES (user_id, goal_type, goal_value, goal_period, start_date, false);
END;
$$;
 �   DROP PROCEDURE public.addgoal(IN user_id bigint, IN goal_type character varying, IN goal_value integer, IN goal_period date, IN start_date date);
       public          postgres    false            �            1255    16451    delete_user(bigint) 	   PROCEDURE     �   CREATE PROCEDURE public.delete_user(IN p_id bigint)
    LANGUAGE plpgsql
    AS $$
BEGIN
    DELETE FROM users WHERE id = p_id;
END;
$$;
 3   DROP PROCEDURE public.delete_user(IN p_id bigint);
       public          postgres    false            �            1255    16461    deleteactivity(bigint) 	   PROCEDURE     �   CREATE PROCEDURE public.deleteactivity(IN activity_id bigint)
    LANGUAGE plpgsql
    AS $$
BEGIN
    DELETE FROM activities WHERE id = activity_id;
END;
$$;
 =   DROP PROCEDURE public.deleteactivity(IN activity_id bigint);
       public          postgres    false            �            1255    16458    deletegoal(bigint) 	   PROCEDURE     �   CREATE PROCEDURE public.deletegoal(IN goal_id bigint)
    LANGUAGE plpgsql
    AS $$
BEGIN
    DELETE FROM goals WHERE id = goal_id;
END;
$$;
 5   DROP PROCEDURE public.deletegoal(IN goal_id bigint);
       public          postgres    false            �            1259    16399    users    TABLE     �   CREATE TABLE public.users (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    birthdate date
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1255    16448    find_user(bigint)    FUNCTION     �   CREATE FUNCTION public.find_user(p_id bigint) RETURNS SETOF public.users
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY SELECT * FROM users WHERE id = p_id;
END;
$$;
 -   DROP FUNCTION public.find_user(p_id bigint);
       public          postgres    false    216            �            1255    16450 %   find_user_by_email(character varying)    FUNCTION     �   CREATE FUNCTION public.find_user_by_email(p_email character varying) RETURNS SETOF public.users
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY SELECT * FROM users WHERE email = p_email;
END;
$$;
 D   DROP FUNCTION public.find_user_by_email(p_email character varying);
       public          postgres    false    216            �            1255    16449 $   find_user_by_name(character varying)    FUNCTION     �   CREATE FUNCTION public.find_user_by_name(p_name character varying) RETURNS SETOF public.users
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY SELECT * FROM users WHERE name = p_name;
END;
$$;
 B   DROP FUNCTION public.find_user_by_name(p_name character varying);
       public          postgres    false    216            �            1259    16421 
   activities    TABLE     �   CREATE TABLE public.activities (
    id bigint NOT NULL,
    userid bigint,
    type character varying(255) NOT NULL,
    duration integer NOT NULL,
    calories integer NOT NULL,
    date date,
    completed boolean DEFAULT false NOT NULL
);
    DROP TABLE public.activities;
       public         heap    postgres    false            �            1255    16462    findactivity(bigint)    FUNCTION     �   CREATE FUNCTION public.findactivity(activity_id bigint) RETURNS SETOF public.activities
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY SELECT * FROM activities WHERE id = activity_id;
END;
$$;
 7   DROP FUNCTION public.findactivity(activity_id bigint);
       public          postgres    false    220            �            1259    16408    goals    TABLE     �   CREATE TABLE public.goals (
    id bigint NOT NULL,
    userid bigint,
    goaltype character varying(255) NOT NULL,
    goalvalue integer NOT NULL,
    goalperiod date,
    completed boolean DEFAULT false NOT NULL,
    start date
);
    DROP TABLE public.goals;
       public         heap    postgres    false            �            1255    16454    findgoal(bigint)    FUNCTION     �   CREATE FUNCTION public.findgoal(goal_id bigint) RETURNS SETOF public.goals
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY SELECT * FROM goals WHERE id = goal_id;
END;
$$;
 /   DROP FUNCTION public.findgoal(goal_id bigint);
       public          postgres    false    218            �            1255    16452    list_users()    FUNCTION     �   CREATE FUNCTION public.list_users() RETURNS SETOF public.users
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY SELECT * FROM users;
END;
$$;
 #   DROP FUNCTION public.list_users();
       public          postgres    false    216            �            1255    16463    listactivities()    FUNCTION     �   CREATE FUNCTION public.listactivities() RETURNS SETOF public.activities
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY SELECT * FROM activities;
END;
$$;
 '   DROP FUNCTION public.listactivities();
       public          postgres    false    220            �            1255    16464    listbyuser(bigint)    FUNCTION     �   CREATE FUNCTION public.listbyuser(user_id bigint) RETURNS SETOF public.activities
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY SELECT * FROM activities WHERE userId = user_id AND completed = true;
END;
$$;
 1   DROP FUNCTION public.listbyuser(user_id bigint);
       public          postgres    false    220            �            1255    16456    listgoals()    FUNCTION     �   CREATE FUNCTION public.listgoals() RETURNS SETOF public.goals
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY SELECT * FROM goals;
END;
$$;
 "   DROP FUNCTION public.listgoals();
       public          postgres    false    218            �            1255    16457    refreshgoal(bigint) 	   PROCEDURE     �   CREATE PROCEDURE public.refreshgoal(IN goal_id bigint)
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE goals
    SET completed = true
    WHERE id = goal_id;
END;
$$;
 6   DROP PROCEDURE public.refreshgoal(IN goal_id bigint);
       public          postgres    false            �            1255    16453 R   update_user(bigint, character varying, character varying, character varying, date) 	   PROCEDURE     P  CREATE PROCEDURE public.update_user(IN p_id bigint, IN p_name character varying, IN p_email character varying, IN p_password character varying, IN p_birthdate date)
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE users
    SET name = p_name, email = p_email, password = p_password, birthdate = p_birthdate
    WHERE id = p_id;
END;
$$;
 �   DROP PROCEDURE public.update_user(IN p_id bigint, IN p_name character varying, IN p_email character varying, IN p_password character varying, IN p_birthdate date);
       public          postgres    false            �            1255    16465 J   updateactivity(bigint, character varying, integer, integer, date, boolean) 	   PROCEDURE     �  CREATE PROCEDURE public.updateactivity(IN activity_id bigint, IN activity_type character varying, IN duration integer, IN calories integer, IN activity_date date, IN completed boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE activities
    SET type = activity_type, duration = duration, calories = calories, date = activity_date, completed = completed
    WHERE id = activity_id;
END;
$$;
 �   DROP PROCEDURE public.updateactivity(IN activity_id bigint, IN activity_type character varying, IN duration integer, IN calories integer, IN activity_date date, IN completed boolean);
       public          postgres    false            �            1255    16459 4   updategoal(bigint, character varying, integer, date) 	   PROCEDURE     (  CREATE PROCEDURE public.updategoal(IN goal_id bigint, IN goal_type character varying, IN goal_value integer, IN goal_period date)
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE goals
    SET goaltype = goal_type, goalvalue = goal_value, goalperiod = goal_period
    WHERE id = goal_id;
END;
$$;
 �   DROP PROCEDURE public.updategoal(IN goal_id bigint, IN goal_type character varying, IN goal_value integer, IN goal_period date);
       public          postgres    false            �            1259    16420    activities_id_seq    SEQUENCE     z   CREATE SEQUENCE public.activities_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.activities_id_seq;
       public          postgres    false    220            �           0    0    activities_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.activities_id_seq OWNED BY public.activities.id;
          public          postgres    false    219            �            1259    16407    goals_id_seq    SEQUENCE     u   CREATE SEQUENCE public.goals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.goals_id_seq;
       public          postgres    false    218            �           0    0    goals_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.goals_id_seq OWNED BY public.goals.id;
          public          postgres    false    217            �            1259    16398    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    216            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    215            :           2604    16424    activities id    DEFAULT     n   ALTER TABLE ONLY public.activities ALTER COLUMN id SET DEFAULT nextval('public.activities_id_seq'::regclass);
 <   ALTER TABLE public.activities ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            8           2604    16411    goals id    DEFAULT     d   ALTER TABLE ONLY public.goals ALTER COLUMN id SET DEFAULT nextval('public.goals_id_seq'::regclass);
 7   ALTER TABLE public.goals ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            7           2604    16402    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            �          0    16421 
   activities 
   TABLE DATA           [   COPY public.activities (id, userid, type, duration, calories, date, completed) FROM stdin;
    public          postgres    false    220   @       �          0    16408    goals 
   TABLE DATA           ^   COPY public.goals (id, userid, goaltype, goalvalue, goalperiod, completed, start) FROM stdin;
    public          postgres    false    218   �@       �          0    16399    users 
   TABLE DATA           E   COPY public.users (id, name, email, password, birthdate) FROM stdin;
    public          postgres    false    216   �@       �           0    0    activities_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.activities_id_seq', 4, true);
          public          postgres    false    219            �           0    0    goals_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.goals_id_seq', 2, true);
          public          postgres    false    217            �           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 6, true);
          public          postgres    false    215            A           2606    16427    activities activities_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.activities
    ADD CONSTRAINT activities_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.activities DROP CONSTRAINT activities_pkey;
       public            postgres    false    220            ?           2606    16414    goals goals_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.goals
    ADD CONSTRAINT goals_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.goals DROP CONSTRAINT goals_pkey;
       public            postgres    false    218            =           2606    16406    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    216            C           2606    16428 !   activities activities_userid_fkey    FK CONSTRAINT        ALTER TABLE ONLY public.activities
    ADD CONSTRAINT activities_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id);
 K   ALTER TABLE ONLY public.activities DROP CONSTRAINT activities_userid_fkey;
       public          postgres    false    216    220    4669            B           2606    16415    goals goals_userid_fkey    FK CONSTRAINT     u   ALTER TABLE ONLY public.goals
    ADD CONSTRAINT goals_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id);
 A   ALTER TABLE ONLY public.goals DROP CONSTRAINT goals_userid_fkey;
       public          postgres    false    216    4669    218            �   c   x�U�K� ��)� ��Y�T����H��+M&o�fz |�\��➡�:oɃ�?��̼�=�T�I&�&�`��O�hAZԔ��&��W����o�HI      �   0   x�3�4�tv���t�420 b#] �L�4�q,uL�b���� ӯ~      �   �   x�e��N�@@�5<[�0\݉�P
l(��̈́�\���i���Q|1�C��,O>,�0�O�%�\@+�hD	!�aI��&����w\}&���#ˍУv3;�Z���䰄�q�e�UgYv���3���t�����=�K����1Ra߽�sl�;^���#�R<�42+�n��!�׸��Yl����x��Ǧ�k��L�,�8�ҋ�w���G�:�k�R���V����,��7(�c      