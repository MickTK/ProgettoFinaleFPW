PGDMP     -                    y           avis #   12.7 (Ubuntu 12.7-0ubuntu0.20.04.1) #   12.7 (Ubuntu 12.7-0ubuntu0.20.04.1) G    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    24660    avis    DATABASE     v   CREATE DATABASE avis WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';
    DROP DATABASE avis;
                fpw    false            ?            1259    24671    amministratore    TABLE     ?   CREATE TABLE public.amministratore (
    id integer NOT NULL,
    nome character varying(20),
    cognome character varying(20),
    email character varying(50),
    password character varying(5),
    username character varying(5)
);
 "   DROP TABLE public.amministratore;
       public         heap    fpw    false            ?            1259    24669    amministratore_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.amministratore_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.amministratore_id_seq;
       public          fpw    false    204            ?           0    0    amministratore_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.amministratore_id_seq OWNED BY public.amministratore.id;
          public          fpw    false    203            ?            1259    24767    donazione_effettuata    TABLE       CREATE TABLE public.donazione_effettuata (
    id integer NOT NULL,
    orario character varying(10),
    quantita character varying(15),
    sessione_id integer,
    utente_cf character varying(16),
    note character varying(200),
    medico_id integer
);
 (   DROP TABLE public.donazione_effettuata;
       public         heap    fpw    false            ?            1259    24763    donazione_effettuata_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.donazione_effettuata_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.donazione_effettuata_id_seq;
       public          fpw    false    219            ?           0    0    donazione_effettuata_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.donazione_effettuata_id_seq OWNED BY public.donazione_effettuata.id;
          public          fpw    false    217            ?            1259    24765 $   donazione_effettuata_sessione_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.donazione_effettuata_sessione_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE public.donazione_effettuata_sessione_id_seq;
       public          fpw    false    219            ?           0    0 $   donazione_effettuata_sessione_id_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE public.donazione_effettuata_sessione_id_seq OWNED BY public.donazione_effettuata.sessione_id;
          public          fpw    false    218            ?            1259    24677    medico    TABLE     ?   CREATE TABLE public.medico (
    id integer NOT NULL,
    nome character varying(50),
    cognome character varying(50),
    cellulare character varying(20),
    email character varying(50),
    asl character varying(50)
);
    DROP TABLE public.medico;
       public         heap    fpw    false            ?            1259    24675    medico_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.medico_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.medico_id_seq;
       public          fpw    false    206            ?           0    0    medico_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.medico_id_seq OWNED BY public.medico.id;
          public          fpw    false    205            ?            1259    24734    messaggio_amministratore    TABLE     ?   CREATE TABLE public.messaggio_amministratore (
    id integer NOT NULL,
    data character varying(20),
    testo character varying(200),
    amministratore_id integer,
    destinatario character varying(20)
);
 ,   DROP TABLE public.messaggio_amministratore;
       public         heap    fpw    false            ?            1259    24732 .   messaggio_amministratore_amministratore_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.messaggio_amministratore_amministratore_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 E   DROP SEQUENCE public.messaggio_amministratore_amministratore_id_seq;
       public          fpw    false    213            ?           0    0 .   messaggio_amministratore_amministratore_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.messaggio_amministratore_amministratore_id_seq OWNED BY public.messaggio_amministratore.amministratore_id;
          public          fpw    false    212            ?            1259    24730    messaggio_amministratore_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.messaggio_amministratore_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.messaggio_amministratore_id_seq;
       public          fpw    false    213            ?           0    0    messaggio_amministratore_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.messaggio_amministratore_id_seq OWNED BY public.messaggio_amministratore.id;
          public          fpw    false    211            ?            1259    24714    messaggio_utente    TABLE     ?   CREATE TABLE public.messaggio_utente (
    id integer NOT NULL,
    data character varying(20),
    testo character varying(200),
    utente_cf character varying(16)
);
 $   DROP TABLE public.messaggio_utente;
       public         heap    fpw    false            ?            1259    24710    messaggio_utente_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.messaggio_utente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.messaggio_utente_id_seq;
       public          fpw    false    210            ?           0    0    messaggio_utente_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.messaggio_utente_id_seq OWNED BY public.messaggio_utente.id;
          public          fpw    false    209            ?            1259    24748    prenotazione    TABLE     ?   CREATE TABLE public.prenotazione (
    id integer NOT NULL,
    orario character varying(10),
    sessione_id integer,
    utente_cf character varying(16)
);
     DROP TABLE public.prenotazione;
       public         heap    fpw    false            ?            1259    24744    prenotazione_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.prenotazione_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.prenotazione_id_seq;
       public          fpw    false    216            ?           0    0    prenotazione_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.prenotazione_id_seq OWNED BY public.prenotazione.id;
          public          fpw    false    214            ?            1259    24746    prenotazione_sessione_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.prenotazione_sessione_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.prenotazione_sessione_id_seq;
       public          fpw    false    216            ?           0    0    prenotazione_sessione_id_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.prenotazione_sessione_id_seq OWNED BY public.prenotazione.sessione_id;
          public          fpw    false    215            ?            1259    24683    sessione    TABLE     ?   CREATE TABLE public.sessione (
    id integer NOT NULL,
    data character varying(15),
    luogo character varying(50),
    inizio character varying(10),
    fine character varying(10)
);
    DROP TABLE public.sessione;
       public         heap    fpw    false            ?            1259    24681    sessione_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.sessione_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.sessione_id_seq;
       public          fpw    false    208            ?           0    0    sessione_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.sessione_id_seq OWNED BY public.sessione.id;
          public          fpw    false    207            ?            1259    24661    utente    TABLE     ?  CREATE TABLE public.utente (
    codicefiscale character varying(16) NOT NULL,
    username character varying(20),
    password character varying(20),
    nome character varying(50),
    cognome character varying(50),
    sesso character varying(1),
    email character varying(50),
    grupposanguigno character varying(3),
    patologie character varying(200),
    fotografia character varying(150),
    nascita character varying(20),
    cellulare character varying(20)
);
    DROP TABLE public.utente;
       public         heap    fpw    false            =           2604    24674    amministratore id    DEFAULT     v   ALTER TABLE ONLY public.amministratore ALTER COLUMN id SET DEFAULT nextval('public.amministratore_id_seq'::regclass);
 @   ALTER TABLE public.amministratore ALTER COLUMN id DROP DEFAULT;
       public          fpw    false    203    204    204            E           2604    24770    donazione_effettuata id    DEFAULT     ?   ALTER TABLE ONLY public.donazione_effettuata ALTER COLUMN id SET DEFAULT nextval('public.donazione_effettuata_id_seq'::regclass);
 F   ALTER TABLE public.donazione_effettuata ALTER COLUMN id DROP DEFAULT;
       public          fpw    false    217    219    219            F           2604    24771     donazione_effettuata sessione_id    DEFAULT     ?   ALTER TABLE ONLY public.donazione_effettuata ALTER COLUMN sessione_id SET DEFAULT nextval('public.donazione_effettuata_sessione_id_seq'::regclass);
 O   ALTER TABLE public.donazione_effettuata ALTER COLUMN sessione_id DROP DEFAULT;
       public          fpw    false    218    219    219            >           2604    24680 	   medico id    DEFAULT     f   ALTER TABLE ONLY public.medico ALTER COLUMN id SET DEFAULT nextval('public.medico_id_seq'::regclass);
 8   ALTER TABLE public.medico ALTER COLUMN id DROP DEFAULT;
       public          fpw    false    206    205    206            A           2604    24737    messaggio_amministratore id    DEFAULT     ?   ALTER TABLE ONLY public.messaggio_amministratore ALTER COLUMN id SET DEFAULT nextval('public.messaggio_amministratore_id_seq'::regclass);
 J   ALTER TABLE public.messaggio_amministratore ALTER COLUMN id DROP DEFAULT;
       public          fpw    false    213    211    213            B           2604    24738 *   messaggio_amministratore amministratore_id    DEFAULT     ?   ALTER TABLE ONLY public.messaggio_amministratore ALTER COLUMN amministratore_id SET DEFAULT nextval('public.messaggio_amministratore_amministratore_id_seq'::regclass);
 Y   ALTER TABLE public.messaggio_amministratore ALTER COLUMN amministratore_id DROP DEFAULT;
       public          fpw    false    212    213    213            @           2604    24717    messaggio_utente id    DEFAULT     z   ALTER TABLE ONLY public.messaggio_utente ALTER COLUMN id SET DEFAULT nextval('public.messaggio_utente_id_seq'::regclass);
 B   ALTER TABLE public.messaggio_utente ALTER COLUMN id DROP DEFAULT;
       public          fpw    false    209    210    210            C           2604    24751    prenotazione id    DEFAULT     r   ALTER TABLE ONLY public.prenotazione ALTER COLUMN id SET DEFAULT nextval('public.prenotazione_id_seq'::regclass);
 >   ALTER TABLE public.prenotazione ALTER COLUMN id DROP DEFAULT;
       public          fpw    false    214    216    216            D           2604    24752    prenotazione sessione_id    DEFAULT     ?   ALTER TABLE ONLY public.prenotazione ALTER COLUMN sessione_id SET DEFAULT nextval('public.prenotazione_sessione_id_seq'::regclass);
 G   ALTER TABLE public.prenotazione ALTER COLUMN sessione_id DROP DEFAULT;
       public          fpw    false    215    216    216            ?           2604    24686    sessione id    DEFAULT     j   ALTER TABLE ONLY public.sessione ALTER COLUMN id SET DEFAULT nextval('public.sessione_id_seq'::regclass);
 :   ALTER TABLE public.sessione ALTER COLUMN id DROP DEFAULT;
       public          fpw    false    207    208    208            ?          0    24671    amministratore 
   TABLE DATA           V   COPY public.amministratore (id, nome, cognome, email, password, username) FROM stdin;
    public          fpw    false    204   ?T       ?          0    24767    donazione_effettuata 
   TABLE DATA           m   COPY public.donazione_effettuata (id, orario, quantita, sessione_id, utente_cf, note, medico_id) FROM stdin;
    public          fpw    false    219   &U       ?          0    24677    medico 
   TABLE DATA           J   COPY public.medico (id, nome, cognome, cellulare, email, asl) FROM stdin;
    public          fpw    false    206   yU       ?          0    24734    messaggio_amministratore 
   TABLE DATA           d   COPY public.messaggio_amministratore (id, data, testo, amministratore_id, destinatario) FROM stdin;
    public          fpw    false    213   ?U       ?          0    24714    messaggio_utente 
   TABLE DATA           F   COPY public.messaggio_utente (id, data, testo, utente_cf) FROM stdin;
    public          fpw    false    210   sV       ?          0    24748    prenotazione 
   TABLE DATA           J   COPY public.prenotazione (id, orario, sessione_id, utente_cf) FROM stdin;
    public          fpw    false    216   ?V       ?          0    24683    sessione 
   TABLE DATA           A   COPY public.sessione (id, data, luogo, inizio, fine) FROM stdin;
    public          fpw    false    208   qW       ?          0    24661    utente 
   TABLE DATA           ?   COPY public.utente (codicefiscale, username, password, nome, cognome, sesso, email, grupposanguigno, patologie, fotografia, nascita, cellulare) FROM stdin;
    public          fpw    false    202   ?W       ?           0    0    amministratore_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.amministratore_id_seq', 1, true);
          public          fpw    false    203            ?           0    0    donazione_effettuata_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.donazione_effettuata_id_seq', 18, true);
          public          fpw    false    217            ?           0    0 $   donazione_effettuata_sessione_id_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('public.donazione_effettuata_sessione_id_seq', 1, false);
          public          fpw    false    218            ?           0    0    medico_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.medico_id_seq', 3, true);
          public          fpw    false    205            ?           0    0 .   messaggio_amministratore_amministratore_id_seq    SEQUENCE SET     ]   SELECT pg_catalog.setval('public.messaggio_amministratore_amministratore_id_seq', 1, false);
          public          fpw    false    212            ?           0    0    messaggio_amministratore_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.messaggio_amministratore_id_seq', 19, true);
          public          fpw    false    211            ?           0    0    messaggio_utente_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.messaggio_utente_id_seq', 7, true);
          public          fpw    false    209            ?           0    0    prenotazione_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.prenotazione_id_seq', 59, true);
          public          fpw    false    214            ?           0    0    prenotazione_sessione_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.prenotazione_sessione_id_seq', 1, false);
          public          fpw    false    215                        0    0    sessione_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.sessione_id_seq', 15, true);
          public          fpw    false    207            J           2606    24724 "   amministratore amministratore_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT amministratore_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.amministratore DROP CONSTRAINT amministratore_pkey;
       public            fpw    false    204            L           2606    24697    medico medico_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.medico
    ADD CONSTRAINT medico_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.medico DROP CONSTRAINT medico_pkey;
       public            fpw    false    206            P           2606    24722 &   messaggio_utente messaggio_utente_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.messaggio_utente
    ADD CONSTRAINT messaggio_utente_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.messaggio_utente DROP CONSTRAINT messaggio_utente_pkey;
       public            fpw    false    210            N           2606    24688    sessione sessione_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.sessione
    ADD CONSTRAINT sessione_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.sessione DROP CONSTRAINT sessione_pkey;
       public            fpw    false    208            H           2606    24665    utente utente_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.utente
    ADD CONSTRAINT utente_pkey PRIMARY KEY (codicefiscale);
 <   ALTER TABLE ONLY public.utente DROP CONSTRAINT utente_pkey;
       public            fpw    false    202            R           2606    24739 *   messaggio_amministratore amministratore_fk    FK CONSTRAINT     ?   ALTER TABLE ONLY public.messaggio_amministratore
    ADD CONSTRAINT amministratore_fk FOREIGN KEY (amministratore_id) REFERENCES public.amministratore(id) NOT VALID;
 T   ALTER TABLE ONLY public.messaggio_amministratore DROP CONSTRAINT amministratore_fk;
       public          fpw    false    2890    204    213            S           2606    24753    prenotazione sessione_fk    FK CONSTRAINT     ?   ALTER TABLE ONLY public.prenotazione
    ADD CONSTRAINT sessione_fk FOREIGN KEY (sessione_id) REFERENCES public.sessione(id) NOT VALID;
 B   ALTER TABLE ONLY public.prenotazione DROP CONSTRAINT sessione_fk;
       public          fpw    false    216    208    2894            U           2606    24772     donazione_effettuata sessione_fk    FK CONSTRAINT     ?   ALTER TABLE ONLY public.donazione_effettuata
    ADD CONSTRAINT sessione_fk FOREIGN KEY (sessione_id) REFERENCES public.sessione(id) NOT VALID;
 J   ALTER TABLE ONLY public.donazione_effettuata DROP CONSTRAINT sessione_fk;
       public          fpw    false    208    219    2894            Q           2606    24725    messaggio_utente utente_fk    FK CONSTRAINT     ?   ALTER TABLE ONLY public.messaggio_utente
    ADD CONSTRAINT utente_fk FOREIGN KEY (utente_cf) REFERENCES public.utente(codicefiscale) NOT VALID;
 D   ALTER TABLE ONLY public.messaggio_utente DROP CONSTRAINT utente_fk;
       public          fpw    false    2888    202    210            T           2606    24782    prenotazione utente_fk    FK CONSTRAINT     ?   ALTER TABLE ONLY public.prenotazione
    ADD CONSTRAINT utente_fk FOREIGN KEY (utente_cf) REFERENCES public.utente(codicefiscale) NOT VALID;
 @   ALTER TABLE ONLY public.prenotazione DROP CONSTRAINT utente_fk;
       public          fpw    false    2888    202    216            V           2606    24787    donazione_effettuata utente_fk    FK CONSTRAINT     ?   ALTER TABLE ONLY public.donazione_effettuata
    ADD CONSTRAINT utente_fk FOREIGN KEY (utente_cf) REFERENCES public.utente(codicefiscale) NOT VALID;
 H   ALTER TABLE ONLY public.donazione_effettuata DROP CONSTRAINT utente_fk;
       public          fpw    false    219    202    2888            ?   7   x?3???L?H?I??M??,????!C????????\N3SsK?"?=... 7??      ?   C   x?34?44??44?t?r??1psw?2?2s?
rZ e??!
|???||?}?L??\@
b???? ?A<      ?   j   x?=??
?0?o?&4I-x??U*x?e??.?lI???*?4?L??rf?M?C????.[b=?j	Ϣ?q6?X?ljE93b?=t??}?z?a???????????$?      ?   p   x??1?0?99??@8*ee?T??#KT??R?%????w?G???Dm7??
???{
?d?C6]?*x??^??????o?e0
????Ψ{AW?\0?????Yť?1^?"^      ?   v   x?ɱ?0??}??\??"??????7???*????e???a??scz????F?!7?R?UY??`e*L|?c/E>???*g???^?\???+??4?&?qY]Liy??!??}???M$#      ?   h   x?m?K
?0 ?ur?b????JA?"????Rp??y?X?4k???.ǐ?sGyr??"Y,??????$?NQ?,S??j??,L??(?#??pF)_??yD?h?$?      ?   Q   x?34?4202?5??52??LTI?I-I?Q????44?24?*??T`T?gl?ehUc???]C#]Ct?PCb???? ???      ?   #  x???AS?0??ۿ¤&)?????E?q?'`???ah??[ZF.?v?;???N?a?&?x|?ɟ^?a??_Z?R???uz?2?XT??J??]?
???j?m-a?.??h?*??֒??? ??0C?o???c'N?T?(?Ӝ%~$zB?ks wk???z???'? bUz??:???4??ay?9x??S!??<c?$??Vܬ??????7+SU????i???[?,e?]ma1??Lِ҇i8!?eyx??????0??}6???M?u????˧/0????wPr^??u?ޓ??     