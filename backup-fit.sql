PGDMP                       |            fit    16.3    16.3 .    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16397    fit    DATABASE     z   CREATE DATABASE fit WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE fit;
                postgres    false            �            1255    16502 H   add_activity(bigint, character varying, integer, integer, date, boolean) 	   PROCEDURE     �  CREATE PROCEDURE public.add_activity(IN p_user_id bigint, IN p_activity_type character varying, IN p_duration integer, IN p_calories integer, IN p_activity_date date, IN p_completed boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO activities (userid, type, duration, calories, date, completed)
    VALUES (p_user_id, p_activity_type, p_duration, p_calories, p_activity_date, p_completed);
END;
$$;
 �   DROP PROCEDURE public.add_activity(IN p_user_id bigint, IN p_activity_type character varying, IN p_duration integer, IN p_calories integer, IN p_activity_date date, IN p_completed boolean);
       public          postgres    false            �            1255    16497 8   add_goal(bigint, character varying, integer, date, date) 	   PROCEDURE     u  CREATE PROCEDURE public.add_goal(IN p_user_id bigint, IN p_goal_type character varying, IN p_goal_value integer, IN p_goal_period date, IN p_start_date date)
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO goals (userid, goaltype, goalvalue, goalperiod, start, completed)
    VALUES (p_user_id, p_goal_type, p_goal_value, p_goal_period, p_start_date, false);
END;
$$;
 �   DROP PROCEDURE public.add_goal(IN p_user_id bigint, IN p_goal_type character varying, IN p_goal_value integer, IN p_goal_period date, IN p_start_date date);
       public          postgres    false            �            1255    16489 G   add_user(character varying, character varying, character varying, date) 	   PROCEDURE     1  CREATE PROCEDURE public.add_user(IN p_name character varying, IN p_email character varying, IN p_password character varying, IN p_birthdate date)
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO users (name, email, password, birthdate) 
    VALUES (p_name, p_email, p_password, p_birthdate);
END;
$$;
 �   DROP PROCEDURE public.add_user(IN p_name character varying, IN p_email character varying, IN p_password character varying, IN p_birthdate date);
       public          postgres    false            �            1255    16503    delete_activity(bigint) 	   PROCEDURE     �   CREATE PROCEDURE public.delete_activity(IN p_activity_id bigint)
    LANGUAGE plpgsql
    AS $$
BEGIN
    DELETE FROM activities WHERE id = p_activity_id;
END;
$$;
 @   DROP PROCEDURE public.delete_activity(IN p_activity_id bigint);
       public          postgres    false            �            1255    16500    delete_goal(bigint) 	   PROCEDURE     �   CREATE PROCEDURE public.delete_goal(IN p_goal_id bigint)
    LANGUAGE plpgsql
    AS $$
BEGIN
    DELETE FROM goals WHERE id = p_goal_id;
END;
$$;
 8   DROP PROCEDURE public.delete_goal(IN p_goal_id bigint);
       public          postgres    false            �            1255    16493    delete_user(bigint) 	   PROCEDURE     �   CREATE PROCEDURE public.delete_user(IN p_id bigint)
    LANGUAGE plpgsql
    AS $$
BEGIN
    DELETE FROM users WHERE id = p_id;
END;
$$;
 3   DROP PROCEDURE public.delete_user(IN p_id bigint);
       public          postgres    false            �            1255    16501    find_activity(bigint)    FUNCTION     �  CREATE FUNCTION public.find_activity(p_activity_id bigint) RETURNS TABLE(id bigint, userid bigint, type character varying, duration integer, calories integer, date date, completed boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY 
    SELECT a.id, a.userid, a.type, a.duration, a.calories, a.date, a.completed 
    FROM activities a 
    WHERE a.id = p_activity_id;
END;
$$;
 :   DROP FUNCTION public.find_activity(p_activity_id bigint);
       public          postgres    false            �            1255    16496    find_goal(bigint)    FUNCTION     �  CREATE FUNCTION public.find_goal(p_goal_id bigint) RETURNS TABLE(id bigint, userid bigint, goaltype character varying, goalvalue integer, goalperiod date, start date, completed boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY 
    SELECT g.id, g.userid, g.goaltype, g.goalvalue, g.goalperiod, g.start, g.completed 
    FROM goals g 
    WHERE g.id = p_goal_id;
END;
$$;
 2   DROP FUNCTION public.find_goal(p_goal_id bigint);
       public          postgres    false            �            1255    16490    find_user(bigint)    FUNCTION     H  CREATE FUNCTION public.find_user(p_id bigint) RETURNS TABLE(id bigint, name character varying, email character varying, password character varying, birthdate date)
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY 
    SELECT u.id, u.name, u.email, u.password, u.birthdate 
    FROM users u 
    WHERE u.id = p_id;
END;
$$;
 -   DROP FUNCTION public.find_user(p_id bigint);
       public          postgres    false            �            1255    16492 %   find_user_by_email(character varying)    FUNCTION     e  CREATE FUNCTION public.find_user_by_email(p_email character varying) RETURNS TABLE(id bigint, name character varying, email character varying, password character varying, birthdate date)
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY 
    SELECT u.id, u.name, u.email, u.password, u.birthdate 
    FROM users u 
    WHERE u.email = p_email;
END;
$$;
 D   DROP FUNCTION public.find_user_by_email(p_email character varying);
       public          postgres    false            �            1255    16491 $   find_user_by_name(character varying)    FUNCTION     a  CREATE FUNCTION public.find_user_by_name(p_name character varying) RETURNS TABLE(id bigint, name character varying, email character varying, password character varying, birthdate date)
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY 
    SELECT u.id, u.name, u.email, u.password, u.birthdate 
    FROM users u 
    WHERE u.name = p_name;
END;
$$;
 B   DROP FUNCTION public.find_user_by_name(p_name character varying);
       public          postgres    false            �            1255    16504    list_activities()    FUNCTION     R  CREATE FUNCTION public.list_activities() RETURNS TABLE(id bigint, userid bigint, type character varying, duration integer, calories integer, date date, completed boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY 
    SELECT a.id, a.userid, a.type, a.duration, a.calories, a.date, a.completed 
    FROM activities a;
END;
$$;
 (   DROP FUNCTION public.list_activities();
       public          postgres    false            �            1255    16505    list_by_user(bigint)    FUNCTION     �  CREATE FUNCTION public.list_by_user(p_user_id bigint) RETURNS TABLE(id bigint, userid bigint, type character varying, duration integer, calories integer, date date, completed boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY 
    SELECT a.id, a.userid, a.type, a.duration, a.calories, a.date, a.completed 
    FROM activities a 
    WHERE a.userid = p_user_id AND a.completed = true;
END;
$$;
 5   DROP FUNCTION public.list_by_user(p_user_id bigint);
       public          postgres    false            �            1255    16498    list_goals()    FUNCTION     U  CREATE FUNCTION public.list_goals() RETURNS TABLE(id bigint, userid bigint, goaltype character varying, goalvalue integer, goalperiod date, start date, completed boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY 
    SELECT g.id, g.userid, g.goaltype, g.goalvalue, g.goalperiod, g.start, g.completed 
    FROM goals g;
END;
$$;
 #   DROP FUNCTION public.list_goals();
       public          postgres    false            �            1255    16494    list_users()    FUNCTION     '  CREATE FUNCTION public.list_users() RETURNS TABLE(id bigint, name character varying, email character varying, password character varying, birthdate date)
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY 
    SELECT u.id, u.name, u.email, u.password, u.birthdate 
    FROM users u;
END;
$$;
 #   DROP FUNCTION public.list_users();
       public          postgres    false            �            1255    16457    refreshgoal(bigint) 	   PROCEDURE     �   CREATE PROCEDURE public.refreshgoal(IN goal_id bigint)
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE goals
    SET completed = true
    WHERE id = goal_id;
END;
$$;
 6   DROP PROCEDURE public.refreshgoal(IN goal_id bigint);
       public          postgres    false            �            1255    16506 K   update_activity(bigint, character varying, integer, integer, date, boolean) 	   PROCEDURE     �  CREATE PROCEDURE public.update_activity(IN p_activity_id bigint, IN p_activity_type character varying, IN p_duration integer, IN p_calories integer, IN p_activity_date date, IN p_completed boolean)
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE activities
    SET type = p_activity_type, duration = p_duration, calories = p_calories, date = p_activity_date, completed = p_completed
    WHERE id = p_activity_id;
END;
$$;
 �   DROP PROCEDURE public.update_activity(IN p_activity_id bigint, IN p_activity_type character varying, IN p_duration integer, IN p_calories integer, IN p_activity_date date, IN p_completed boolean);
       public          postgres    false            �            1255    16499 5   update_goal(bigint, character varying, integer, date) 	   PROCEDURE     ;  CREATE PROCEDURE public.update_goal(IN p_goal_id bigint, IN p_goal_type character varying, IN p_goal_value integer, IN p_goal_period date)
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE goals 
    SET goaltype = p_goal_type, goalvalue = p_goal_value, goalperiod = p_goal_period 
    WHERE id = p_goal_id;
END;
$$;
 �   DROP PROCEDURE public.update_goal(IN p_goal_id bigint, IN p_goal_type character varying, IN p_goal_value integer, IN p_goal_period date);
       public          postgres    false            �            1255    16495 R   update_user(bigint, character varying, character varying, character varying, date) 	   PROCEDURE     R  CREATE PROCEDURE public.update_user(IN p_id bigint, IN p_name character varying, IN p_email character varying, IN p_password character varying, IN p_birthdate date)
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE users 
    SET name = p_name, email = p_email, password = p_password, birthdate = p_birthdate 
    WHERE id = p_id;
END;
$$;
 �   DROP PROCEDURE public.update_user(IN p_id bigint, IN p_name character varying, IN p_email character varying, IN p_password character varying, IN p_birthdate date);
       public          postgres    false            �            1259    16421 
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
       public         heap    postgres    false            �            1259    16420    activities_id_seq    SEQUENCE     z   CREATE SEQUENCE public.activities_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.activities_id_seq;
       public          postgres    false    220            �           0    0    activities_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.activities_id_seq OWNED BY public.activities.id;
          public          postgres    false    219            �            1259    16408    goals    TABLE     �   CREATE TABLE public.goals (
    id bigint NOT NULL,
    userid bigint,
    goaltype character varying(255) NOT NULL,
    goalvalue integer NOT NULL,
    goalperiod date,
    completed boolean DEFAULT false NOT NULL,
    start date
);
    DROP TABLE public.goals;
       public         heap    postgres    false            �            1259    16407    goals_id_seq    SEQUENCE     u   CREATE SEQUENCE public.goals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.goals_id_seq;
       public          postgres    false    218            �           0    0    goals_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.goals_id_seq OWNED BY public.goals.id;
          public          postgres    false    217            �            1259    16399    users    TABLE     �   CREATE TABLE public.users (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    birthdate date
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    16398    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    216            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    215            :           2604    16424    activities id    DEFAULT     n   ALTER TABLE ONLY public.activities ALTER COLUMN id SET DEFAULT nextval('public.activities_id_seq'::regclass);
 <   ALTER TABLE public.activities ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    220    220            8           2604    16411    goals id    DEFAULT     d   ALTER TABLE ONLY public.goals ALTER COLUMN id SET DEFAULT nextval('public.goals_id_seq'::regclass);
 7   ALTER TABLE public.goals ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            7           2604    16402    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            �          0    16421 
   activities 
   TABLE DATA           [   COPY public.activities (id, userid, type, duration, calories, date, completed) FROM stdin;
    public          postgres    false    220   �F       �          0    16408    goals 
   TABLE DATA           ^   COPY public.goals (id, userid, goaltype, goalvalue, goalperiod, completed, start) FROM stdin;
    public          postgres    false    218   G       �          0    16399    users 
   TABLE DATA           E   COPY public.users (id, name, email, password, birthdate) FROM stdin;
    public          postgres    false    216   cG       �           0    0    activities_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.activities_id_seq', 5, true);
          public          postgres    false    219            �           0    0    goals_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.goals_id_seq', 4, true);
          public          postgres    false    217            �           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 7, true);
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
       public          postgres    false    218    4669    216            �   c   x�U�K� ��)� ��Y�T����H��+M&o�fz |�\��➡�:oɃ�?��̼�=�T�I&�&�`��O�hAZԔ��&��W����o�HI      �   8   x�3�4�tv���t�420 b#] �L�4�q,uL�L8͉Qh����� �^      �   �   x�e��N�@��5\G��0-K&8�5����X�C���x)ޘ4� irrVo���-��Gi�PjR�CFhy7�ĺ�����β�-�m�&�?�r��s��p�DN���)�@/pg�9�`HE͚�����'\���V�ԛ(?qDX�sv�V̎�x7�֭+�
Br��O(�٪�����:�Z�Z���>���O��T{`��z��V�^V��Ǿ9v�m���6�C�h�����c�     